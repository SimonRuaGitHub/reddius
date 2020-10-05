package com.reddius.service;

import java.time.Instant;
import java.time.ZoneId;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.data.convert.ThreeTenBackPortConverters.ZoneIdToStringConverter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.reddius.dto.AuthenticationResponse;
import com.reddius.dto.LoginRequest;
import com.reddius.dto.RegisterRequest;
import com.reddius.dto.RefreshTokenRequest;
import com.reddius.exceptions.SpringReddiusException;
import com.reddius.model.NotificationEmail;
import com.reddius.model.User;
import com.reddius.model.VerificationToken;
import com.reddius.repository.UserRepository;
import com.reddius.repository.VerificationTokenRepository;
import com.reddius.security.JwtProvider;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthService {
	
	private final PasswordEncoder passwordEncoder;
	
	private final UserRepository userRepository;
	
	private final VerificationTokenRepository verTokenRepository;
	
	private final MailService mailService;
	
	private final AuthenticationManager authManager;
	
	private final JwtProvider jwtProvider;
	
	private final RefreshTokenService refreshTokenService;
	
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

	public AuthenticationResponse login(LoginRequest loginReq) {
		
		Authentication authentication = authManager.authenticate( new UsernamePasswordAuthenticationToken(loginReq.getUsername(), loginReq.getPassword()) );
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String token = jwtProvider.generateToken(authentication);

		return AuthenticationResponse.builder()
				                     .authenticationToken(token)
				                     .expiresAt(Instant.now().plusMillis(jwtProvider.getJwtExpirationInMillis()))
				                     .refreshToken(refreshTokenService.generateRefreshToken().getToken())
				                     .username(loginReq.getUsername())
				                     .build();
	}
	
	public AuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
		
		   refreshTokenService.validateRefreshToken(refreshTokenRequest.getRefreshToken());
		   String newJwtToken = jwtProvider.generateTokenWithUsername(refreshTokenRequest.getUsername());
		   
		   return AuthenticationResponse.builder()
						                .authenticationToken(newJwtToken)
						                .expiresAt(Instant.now().plusMillis(jwtProvider.getJwtExpirationInMillis()))
						                .refreshToken(refreshTokenService.generateRefreshToken().getToken())
						                .username(refreshTokenRequest.getUsername())
						                .build();
	}
	
	public void deleteToken(String refreshToken) {
		   refreshTokenService.deleteRefreshToken(refreshToken);
	}
	
	public User getCurrentUser() {
		   org.springframework.security.core.userdetails.User ppalUser = (org.springframework.security.core.userdetails.User)  SecurityContextHolder
				   .getContext().getAuthentication().getPrincipal();
		   
		   return userRepository.findByUsername(ppalUser.getUsername())
				                .orElseThrow( () -> new SpringReddiusException("User name not found - "+ ppalUser.getUsername()) );
	}
	
}
