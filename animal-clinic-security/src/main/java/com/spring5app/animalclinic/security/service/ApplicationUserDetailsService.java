package com.spring5app.animalclinic.security.service;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.spring5app.animalclinic.services.UserService;

@Service
public class ApplicationUserDetailsService implements UserDetailsService {

	private final UserService userService;

	public ApplicationUserDetailsService(UserService userService) {
		this.userService = userService;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		if (username == null) {
			throw new UsernameNotFoundException("argument null.");
		}

		com.spring5app.animalclinic.model.User user = userService.findByUserName(username);

		if (user == null) {
			throw new UsernameNotFoundException("username not found.");
		}

		User userSecurity = new User(user.getUserName(), user.getPassword(), new ArrayList<>());
		return userSecurity;

	}

}
