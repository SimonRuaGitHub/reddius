package com.reddius.service;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.reddius.exceptions.SpringReddiusException;
import com.reddius.model.User;
import com.reddius.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserDetailsServiceImp implements UserDetailsService {
	
	private final UserRepository userRepository;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username).orElseThrow(() ->new SpringReddiusException("User was not found with username: "+username));
		return new org.springframework.security.core.userdetails.User(user.getUsername(), 
				                                                      user.getPassword(), 
				                                                      user.isEnabled(), 
				                                                      true, true, true,
				                                                      (Collection<? extends GrantedAuthority>) Collections.singletonList(new SimpleGrantedAuthority("USER")));			
	}


}
