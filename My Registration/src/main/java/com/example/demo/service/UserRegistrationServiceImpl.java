package com.example.demo.service;

import com.example.demo.model.MyAppUser;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserRegistrationServiceImpl implements UserRegistrationService{
	@Autowired
	private UserRepository userRepository;


	
	@Override
	public void createUser(MyAppUser user) {
		
		userRepository.save(user);
		
		System.out.println("***** User Saved  *****");
	}

	

}