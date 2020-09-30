package web.project.backend.security.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import web.project.backend.security.CookieUtil;
import web.project.backend.security.RedisUtil;
import web.project.backend.security.service.MyUserDetailsService;

public class JwtAuthFilter extends OncePerRequestFilter {

	@Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CookieUtil cookieUtil;

    @Autowired
    private RedisUtil redisUtil;
    

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

    	String refreshJwt = null;
    	
    	try {
            String jwt = getJwtFromRequest(httpServletRequest);
            
            if (StringUtils.hasText(jwt) && jwtUtil.validateToken(jwt)) {

            	String userId = jwtUtil.getUsername(jwt);

                UserDetails userDetails = userDetailsService.loadUserByUsername(userId);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
            
        }

        catch (ExpiredJwtException e){
            Cookie refreshToken = cookieUtil.getCookie(httpServletRequest,JwtUtil.REFRESH_TOKEN_NAME);
            if(refreshToken!=null){
                refreshJwt = refreshToken.getValue();
            }
        }
    	catch (Exception ex) {
            logger.error("Could not set user authentication in security context", ex);
        }

        /*try{
            if(refreshJwt != null){
                refreshUname = redisUtil.getData(refreshJwt);
                
                if(refreshUname != null && refreshUname.equals(jwtUtil.getUsername(refreshJwt))){
                    UserDetails userDetails = userDetailsService.loadUserByUsername(refreshUname);
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                    usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

                    Member member = memberRepository.findByloginid(refreshUname)
                    		.orElseThrow(() -> new UsernameNotFoundException("User not found"));
                    
                    String newToken =jwtUtil.generateToken(member);
                    Cookie newAccessToken = cookieUtil.createCookie(JwtUtil.ACCESS_TOKEN_NAME,newToken);
                    httpServletResponse.addCookie(newAccessToken);
                    }
            }
        }catch(ExpiredJwtException e){

        }*/

        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }
    
    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7, bearerToken.length());
        }
        return null;
    }
}