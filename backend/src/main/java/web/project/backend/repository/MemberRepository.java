package web.project.backend.repository;

import java.util.List;
import java.util.Optional;

import web.project.backend.entity.Member;


public interface MemberRepository {
	
	Member save(Member member);
	
	Optional<Member> findByloginid(String id);
	
	Optional<Member> findByemail(String email);
	
	Optional<Member> findByidx(Long id);
	
	List<Member> findAll();
	
	void delete(Member member);
	
	


}
