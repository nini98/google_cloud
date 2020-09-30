package project.backend.service;
/*package project.backend.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import web.project.backend.BackendApplication;
import web.project.backend.orm.user;
import web.project.backend.repository.MemberRepository;
import web.project.backend.service.MemberService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(classes=BackendApplication.class)
@Transactional
class MemberServiceIntegrationTest {
	
	@Autowired
	MemberService memberService;
	@Autowired
	MemberRepository memberRepository;

	@Test
	public void 회원가입() throws Exception {
		// Given
		user member = new user();
		member.setName("hello");
		// When
		Long saveId = memberService.join(member);
		// Then
		user findMember = memberRepository.findById(saveId).get();
		assertEquals(member.getName(), findMember.getName());
	}

	@Test
	public void 중복_회원_예외() throws Exception {
		// Given
		user member1 = new user();
		member1.setName("spring");
		user member2 = new user();
		member2.setName("spring");
		// When
		memberService.join(member1);
		IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));// 예외가
																												// 발생해야
																												// 한다.
		assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
	}
}*/