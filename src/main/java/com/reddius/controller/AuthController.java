package com.reddius.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reddius.dto.AuthenticationResponse;
import com.reddius.dto.LoginRequest;
import com.reddius.dto.RefreshTokenRequest;
import com.reddius.dto.RegisterRequest;
import com.reddius.service.AuthService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {
	
	private final AuthService authService;

	@PostMapping("/signup")
	public ResponseEntity<String> signup(@RequestBody RegisterRequest registerRequest) {
			authService.signup(registerRequest);
			return new ResponseEntity<String>("User registration successful", HttpStatus.CREATED);
	}
	
	@GetMapping("/accountVerification/{token}")
    public ResponseEntity<String> verifyAccount(@PathVariable String token) {
    	   authService.verifyAccount(token);
    	   return new ResponseEntity<String>("Account activated successfully",HttpStatus.OK);
    }
	
	@PostMapping("/login")
	public AuthenticationResponse login(@RequestBody LoginRequest loginRequest) {
		return authService.login(loginRequest);
	}
	
	@PostMapping("/refresh/token")
    public ResponseEntity<AuthenticationResponse> refreshToken(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
		
    	   return new ResponseEntity<AuthenticationResponse>(authService.refreshToken(refreshTokenRequest), HttpStatus.CREATED);
    }
	
	@PostMapping("/logout")
	public ResponseEntity<String> deleteToken(@Valid @RequestBody String refreshToken){
		   authService.deleteToken(refreshToken);
		   return new ResponseEntity<String>("Refresh Token Deleted Successfully", HttpStatus.OK);
	}
	
	
	
}
