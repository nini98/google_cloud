package web.project.backend.security.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import web.project.backend.entity.Member;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class MyUserDetails implements UserDetails {
    /**
	 * 
	 */
	private static final long serialVersionUID = -2464002246606807570L;
	private Long id;
    private String loginid;
    private String name;
    private String email;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;
    private Map<String, Object> attributes;

    public MyUserDetails(Long id,String loginid, String name, String email, String password,
                             Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.loginid = loginid;
        this.name = name;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    public static MyUserDetails create(Member member) {
        List<GrantedAuthority> authorities = Collections.
                singletonList(new SimpleGrantedAuthority("USER"));

        return new MyUserDetails(
        		member.getIdx(),
        		member.getLoginid(),
        		member.getName(),
        		member.getEmail(),
        		member.getPassword(),
                authorities
        );
    }

    public static MyUserDetails create(Member member, Map<String, Object> attributes) {
        MyUserDetails customUserDetails = MyUserDetails.create(member);
        customUserDetails.setAttributes(attributes);
        return customUserDetails;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

	@Override
	public String getUsername() {
		return loginid;
	}

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public String getName() {
        return name;
    }
}
