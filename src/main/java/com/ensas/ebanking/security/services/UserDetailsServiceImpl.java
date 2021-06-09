package com.ensas.ebanking.security.services;

import com.ensas.ebanking.models.User;
import com.ensas.ebanking.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	UserService userService;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userService.getByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + email));
		return UserDetailsImpl.build(user);
	}


}
