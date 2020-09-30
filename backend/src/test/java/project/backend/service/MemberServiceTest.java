package project.backend.service;

import static org.assertj.core.api.Assertions.*;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import web.project.backend.BackendApplication;
import web.project.backend.entity.Member;
import web.project.backend.repository.MemberRepository;
import web.project.backend.service.MemberService;

@SpringBootTest(classes=BackendApplication.class)
@Transactional
class MemberServiceTest {

	@Autowired
	MemberService memberService;
	@Autowired
	MemberRepository memberRepository;
	
	@Test
	public void 이메일찾기() {
		Member member = new Member("test123","test","test","test1@test.com");
		
		memberRepository.save(member);
		
		
		Member result = memberRepository.findByemail(member.getEmail()).get();
		
		assertThat(result).isEqualTo(member);
	}

}
