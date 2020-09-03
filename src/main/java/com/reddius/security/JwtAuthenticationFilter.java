package com.reddius.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{
	
	@Autowired
	private JwtProvider jwtProvider;
	
	@Autowired
	private UserDetailsService userDetailsService;
	

	@Override
	protected void doFilterInternal(HttpServletRequest request, 
									HttpServletResponse response,
									FilterChain filterChain)
			throws ServletException, IOException {
		
      String jwtFromRequest = getJwtFromRequest(request);
      
      if (jwtFromRequest != null && !jwtFromRequest.isEmpty() && jwtProvider.validateToken(jwtFromRequest)) {
    	  String username = jwtProvider.getUsernameFromJwt(jwtFromRequest);
    	  
    	  UserDetails userDetails = userDetailsService.loadUserByUsername(username);
    	  UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    	  authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
    	  
    	  SecurityContextHolder.getContext().setAuthentication(authentication);
      }
		
         filterChain.doFilter(request, response);
	}

	private String getJwtFromRequest(HttpServletRequest request) {
		
		String bearerToken = request.getHeader("Authorization");
		
		String jwt = "";
		
		if(bearerToken != null && !bearerToken.isEmpty() && bearerToken.startsWith("Bearer"))	
			jwt = bearerToken.substring(7);
		
		return jwt;
		
	}

}
