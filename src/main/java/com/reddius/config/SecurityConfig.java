package com.reddius.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.reddius.security.JwtAuthenticationFilter;

import lombok.AllArgsConstructor;

@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	private final UserDetailsService userDetailsService;
	private final JwtAuthenticationFilter jwtAuthenticationFilter;
	
	@Bean(BeanIds.AUTHENTICATION_MANAGER)
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		System.out.println("---------- Creating authenticationManager bean ---------------");
		return super.authenticationManagerBean();
	}

	@Override
	public void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.cors().and()
		            .csrf().disable() //as we are using stateless (REST) and Json Web Token can be disabled
		            .authorizeRequests()
		            .antMatchers("/api/auth/**","/dummy/**")
		            .permitAll()
		            .antMatchers(HttpMethod.GET, "/api/subreddius","/api/posts/all","/api/posts/{id}","/api/comments/by-postId/{id}","/api/posts/page/{page}")
		            .permitAll()
		            .antMatchers("/v2/api-docs",
		            		     "/configuration/ui",
		            		     "/swagger-resources/**",
		            		     "/configuration/security",
		            		     "/swagger-ui.html",
		            		     "/webjars/**")
		            .permitAll()
		            .anyRequest()
		            .authenticated()
		            .and()
		            .sessionManagement()
		            .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		httpSecurity.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		System.out.println("---------- Setting up Authentication Manager Builder ---------------");
		authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		   return new BCryptPasswordEncoder();
	}
}
