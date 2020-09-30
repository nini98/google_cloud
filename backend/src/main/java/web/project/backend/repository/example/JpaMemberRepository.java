/*package web.project.backend.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;

import web.project.backend.orm.user;


public class JpaMemberRepository implements MemberRepository {
	
	@PersistenceContext
	private final EntityManager em;
	
	@Autowired
	public JpaMemberRepository(EntityManager em) {
		this.em = em;
	}

	@Override
	public user save(user member) {
		em.persist(member);
		return member;
	}

	@Override
	public Optional<user> findById(Long id) {
		user member = em.find(user.class, id);
		return Optional.ofNullable(member);
	}

	@Override
	public Optional<user> findByName(String name) {
		List<user> result = em.createQuery("select m from Member m where m.name = :name",user.class)
							.setParameter("name", name)
							.getResultList();
		return result.stream().findAny();
	}

	@Override
	public List<user> findAll() {
		List<user> result = em.createQuery("select m from Member m",user.class)
								.getResultList();
		
		return result;
	}

}
*/