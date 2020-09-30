package web.project.backend.security.service;

import javax.xml.ws.http.HTTPException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import web.project.backend.entity.Member;
import web.project.backend.repository.MemberRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	MemberRepository memberRepository;

	@Override
    @Transactional
    public UserDetails loadUserByUsername(String loginId)
            throws UsernameNotFoundException {
        Member member = memberRepository.findByloginid(loginId)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found with loginId : " + loginId)
        );

        return MyUserDetails.create(member);
    }

    @Transactional
    public UserDetails loadUserById(Long id) {
    	Member member = memberRepository.findByidx(id).orElseThrow(
            () -> new HTTPException(404)
        );

        return MyUserDetails.create(member);
    }

}
