package com.reddius.service;

import java.time.Instant;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.reddius.dto.RegisterRequest;
import com.reddius.exceptions.SpringReddiusException;
import com.reddius.model.NotificationEmail;
import com.reddius.model.User;
import com.reddius.model.VerificationToken;
import com.reddius.repository.UserRepository;
import com.reddius.repository.VerificationTokenRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthService {
	
	private final PasswordEncoder passwordEncoder;
	
	private final UserRepository userRepository;
	
	private final VerificationTokenRepository verTokenRepository;
	
	private final MailService mailService;
	
	@Transactional
	public void signup(RegisterRequest regRequest) {
		   User user = new User();
		   user.setUsername(regRequest.getUsername());
		   user.setEmail(regRequest.getEmail());
		   user.setPassword(passwordEncoder.encode(regRequest.getPassword()));
		   user.setCreationDate(Instant.now());
		   user.setEnabled(false);
		   
		   userRepository.save(user);
		   
		   String token = generateVerificationtToken(user);
		   mailService.sendMail(new NotificationEmail("Welcome from Reddius", user.getEmail(), "Thank you for Signup to Reddius please click on"
		   		+ " url to activate your account: http://localhost:8080/api/auth/accountVerification/" + token));
	}
	
	private String generateVerificationtToken(User user) {
		String token = UUID.randomUUID().toString();
	    VerificationToken verificationToken = new VerificationToken();
	    verificationToken.setToken(token);
	    verificationToken.setUser(user);
	    verificationToken.setExperationDate(Instant.now());
	    
	    verTokenRepository.save(verificationToken);
	    
	    return token;
	}

	public void verifyAccount(String token) {
		Optional<VerificationToken> optVerifToken = verTokenRepository.findByToken(token);
		optVerifToken.orElseThrow(() -> new SpringReddiusException("Invalid Token"));
		fetchUserAndEnable(optVerifToken.get());
	}

	@Transactional
	private void fetchUserAndEnable(VerificationToken verificationToken) {
		String username = verificationToken.getUser().getUsername();
		User user = userRepository.findByUsername(username).orElseThrow(() -> new SpringReddiusException("The user "+username+" has been not found"));
		user.setEnabled(true);
		userRepository.save(user);
	}
}
