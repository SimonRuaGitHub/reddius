package com.reddius.service;

import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;

@SpringBootTest
public class AuthenticationServiceTest {
	
	private AuthService authService;
	
	@Autowired
	private AuthenticationManager authManager;

	@Test
	public void testUserLoginIsSuccessful() {
		
		authService = Mockito.mock(AuthService.class);

	}
}
