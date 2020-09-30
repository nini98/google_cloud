/*package web.project.backend.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


import web.project.backend.orm.user;


public class MemoryMemberRepository implements MemberRepository {

	
	private static Map<Long,user> store = new HashMap<>();
	private static long sequence = 0L;
	
	@Override
	public user save(user member) {
		member.setId(++sequence);
		store.put(member.getId(), member);
		return member;
	}

	@Override
	public Optional<user> findById(Long id) {
		return Optional.ofNullable(store.get(id));
	}

	@Override
	public Optional<user> findByName(String name) {
		return store.values().stream()
				.filter(member -> member.getName().equals(name))
				.findAny();
	}

	@Override
	public List<user> findAll() {
		// TODO Auto-generated method stub
		return new ArrayList<>(store.values());
	}
	
	public void clearStore() {
		store.clear();
	}

}
*/