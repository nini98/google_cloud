package web.project.backend.controller;

import java.util.Collections;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j2;
import web.project.backend.Exception.ConflictException;
import web.project.backend.entity.Member;
import web.project.backend.security.CookieUtil;
import web.project.backend.security.CurrentUser;
import web.project.backend.security.RedisUtil;
import web.project.backend.security.jwt.JwtUtil;
import web.project.backend.security.service.MyUserDetails;
import web.project.backend.service.MemberService;

@RestController
@RequestMapping("blog/Auth")
@Log4j2
public class AuthController {
	
	@Autowired
	private MemberService memberService;
	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	private CookieUtil cookieUtil;
	@Autowired
	private RedisUtil redisUtil;

    private final String AUTHORIZATION_HEADER = "Authorization";
    
	@PostMapping("/signUp")
	public ResponseEntity<?> signUp(@RequestBody Member register) {
		
		try{
			if(!memberService.signUp(register))
			{
			}
			return ResponseEntity.ok("가입완료");
		} catch (ConflictException ce) {
			log.error(ce.getLocalizedMessage());
			return new ResponseEntity<>(ce.getFieldName(), HttpStatus.CONFLICT);
		}
		catch (Exception e) {
			log.error(e.getLocalizedMessage());
			return new ResponseEntity<>(Collections.singletonMap("SignUpErrorException",
                    e.getLocalizedMessage()), HttpStatus.BAD_REQUEST);
		}
		
	}
	@PostMapping("/signIn")
	public ResponseEntity<?> signIn(@RequestBody Member login,
					            HttpServletRequest req,
					            HttpServletResponse res) throws AuthenticationException {
		
		
		if(memberService.signIn(login))
		{
			try {
				final String token = "Bearer " + jwtUtil.generateToken(login);
	            final String refreshJwt = jwtUtil.generateRefreshToken(login);
	            Cookie refreshToken = cookieUtil.createCookie(JwtUtil.REFRESH_TOKEN_NAME, refreshJwt);
	            redisUtil.setDataExpire(refreshJwt, login.getLoginid(), JwtUtil.REFRESH_TOKEN_VALIDATION_SECOND);
	            res.addHeader(AUTHORIZATION_HEADER, token);
	            res.addCookie(refreshToken);
	            return ResponseEntity.ok("로그인 완료");
			} catch (Exception e) {
				return new ResponseEntity<>(Collections.singletonMap("JWTException",
	                    e.getLocalizedMessage()), HttpStatus.UNAUTHORIZED);
	        }
		}
		else
		{
			return new ResponseEntity<>(Collections.singletonMap("LoginException",
                    "비밀번호 또는 아이디가 틀렸습니다."), HttpStatus.UNAUTHORIZED);
		}
	}
	
	@PostMapping("/info")
    //@PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> getCurrentUser(@CurrentUser MyUserDetails myUserDetails) {
        //log.debug("REST request to get user : {}", MyUserDetails.getEmail());
		
		Member member = memberService.findOne(myUserDetails.getUsername())
				.orElseThrow(() ->
                new UsernameNotFoundException("User not found with loginId : " + myUserDetails.getId()));
		
        return ResponseEntity.ok(member);
    }
	
	@PostMapping("/logout")
	public ResponseEntity<?> MemberLogout(HttpServletRequest req, HttpServletResponse res) throws AuthenticationException {
		
		try {
			final Cookie jwtToken = cookieUtil.getCookie(req,JwtUtil.REFRESH_TOKEN_NAME);
			String jwt = jwtToken.getValue();
			redisUtil.deleteData(jwt);
			
			return ResponseEntity.ok("로그아웃 완료");
		} catch (Exception e) {
			return new ResponseEntity<>(Collections.singletonMap("LogoutErrorException",
                    e.getLocalizedMessage()), HttpStatus.BAD_REQUEST);
        }
	}
}
