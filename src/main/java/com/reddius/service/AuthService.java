package com.reddius.service;

import java.time.Instant;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.reddius.dto.RegisterRequest;
import com.reddius.model.User;
import com.reddius.repository.UserRepository;

//@Service
public class AuthService {
	
	private PasswordEncoder passwordEncoder;
	
	private UserRepository userRepository;
	
	//@Autowired
	public AuthService(final PasswordEncoder passwordEncoder, final UserRepository userRepository) {
		this.passwordEncoder = passwordEncoder;
		this.userRepository = userRepository;
	}

	@Transactional
	public void signup(RegisterRequest regRequest) {
		   User user = new User();
		   user.setUsername(regRequest.getUsername());
		   user.setEmail(regRequest.getEmail());
		   user.setPassword(passwordEncoder.encode(regRequest.getPassword()));
		   user.setCreationDate(Instant.now());
		   user.setEnabled(false);
		   
		   userRepository.save(user);
	}
}
