package com.ensas.ebanking.security.services;

import com.ensas.ebanking.models.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ensas.ebanking.models.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Data
public class UserDetailsImpl implements UserDetails {
	private static final long serialVersionUID = 1L;
	private User user;
	private Collection<? extends GrantedAuthority> authorities;

	public UserDetailsImpl(User user,Collection<? extends GrantedAuthority> authorities) {
		this.user = user;
		this.authorities = authorities;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public static UserDetailsImpl build(User user) {
		Set<Role> roles = user.getRoles();
		System.out.println(roles);
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();

		for (Role role : roles) {
			authorities.add(new SimpleGrantedAuthority(role.getName()));
		}

		return new UserDetailsImpl(user, authorities);
	}

	public Long getId() {
		return user.getUserId();
	}

	public String getEmail() {
		return user.getEmail();
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getEmail();
	} //mail as username

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
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UserDetailsImpl userx = (UserDetailsImpl) o;
		return Objects.equals(this.user.getUserId(), user.getUserId());
	}

}
