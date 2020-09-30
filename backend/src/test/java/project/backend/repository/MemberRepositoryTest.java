package project.backend.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import web.project.backend.entity.Member;
import web.project.backend.repository.JpaMemberRepository;

import static org.assertj.core.api.Assertions.*;

import javax.transaction.Transactional;

@Transactional
class MemberRepositoryTest {

	@Autowired
	JpaMemberRepository repository;
	
	@Test
	public void 이메일찾기() {
		Member member = new Member("test123","test","test","test1@test.com");
		
		repository.save(member);
		
		
		Member result = repository.findByemail(member.getEmail()).get();
		
		assertThat(result).isEqualTo(member);
	}
}