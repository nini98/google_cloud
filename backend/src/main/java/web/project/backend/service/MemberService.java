package web.project.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import web.project.backend.Exception.ConflictException;
import web.project.backend.entity.Member;
import web.project.backend.repository.JpaMemberRepository;


@Transactional
@Service
public class MemberService {

	@Autowired
	private JpaMemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
	
	private boolean validateDubplicateLoginId(Member member) {
		return memberRepository.findByloginid(member.getLoginid()).isPresent();
	}
	
	private boolean validateDubplicateEmail(Member member) {
		return memberRepository.findByemail(member.getEmail()).isPresent();
	}
	
	private boolean validateLoginMemberInfo(Member member) {
		Member m = memberRepository.findByloginid(member.getLoginid())
										.orElse(null);
		
		//member가 없음
		if(m == null)
			return false;
		else
		{
			if(passwordEncoder.matches(member.getPassword(), m.getPassword()))
				return true;
			else
				return false;
		}
	}
	
	/*
	 * 회원 가입
	 * */
	public boolean signUp(Member member) throws Exception {
		//같은 이름이 있는 중복 회원 x
		
		if(validateDubplicateLoginId(member))
		{
			throw new ConflictException("LoginId", member.getLoginid());
		}
		else
		{
			if(validateDubplicateEmail(member))
			{
				throw new ConflictException("Email", member.getEmail());
			}
			Member saveMember = new Member(member.getLoginid(), 
										member.getName(), 
										passwordEncoder.encode(member.getPassword()),
										member.getEmail());

			memberRepository.save(saveMember);
		}
		
		return true;
	}
	
	/*
	 * 로그인
	 * */
	public boolean signIn(Member member) {
		
		if(validateLoginMemberInfo(member))
			return true;
		else
			return false;
	}
	
	/*
	 * 전체 회원 조회
	 * */
	public List<Member> findMembers() {
		return memberRepository.findAll();
	}
	
	public Optional<Member> findOne(String memberId) {
		return memberRepository.findByloginid(memberId);	
	}
	
	public void removeMember(Member member) {
		
		memberRepository.delete(member);
	}

}
