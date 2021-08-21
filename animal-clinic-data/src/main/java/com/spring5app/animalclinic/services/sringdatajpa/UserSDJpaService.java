package com.spring5app.animalclinic.services.sringdatajpa;

import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.spring5app.animalclinic.model.User;
import com.spring5app.animalclinic.repositories.UserRepository;
import com.spring5app.animalclinic.services.UserService;

@Service
@Profile("springdatajpa")
public class UserSDJpaService implements UserService {
	
	private final UserRepository userService;
	
	public UserSDJpaService(UserRepository userRepository)
	{
		userService = userRepository;
	}

	@Override
	public Set<User> findAll() {
		
		final Set<User> user = new HashSet<>();
		userService.findAll().forEach(user::add);
		return user;
	}

	@Override
	public User findById(Long id) {
		return userService.findById(id).orElse(null);
	}

	@Override
	public User save(User user) {
		return userService.save(user);
	}

	@Override
	public void delete(User user) {
		userService.delete(user);		
	}

	@Override
	public void deleteById(Long id) {
		userService.deleteById(id);
		
	}

	@Override
	public User findByUserName(String userName) {
		return userService.findByUserName(userName).orElse(null);
	}

}
