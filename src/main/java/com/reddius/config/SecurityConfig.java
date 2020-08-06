package com.reddius.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Override
	public void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.csrf().disable() //as we are using stateless (REST) and Json Web Token this can be disabled
		            .authorizeRequests()
		            .antMatchers("/api/auth/**","/dummy/**")
		            .permitAll()
		            .anyRequest()
		            .authenticated();
	}
	
	@Override
	public void configure(AuthenticationManagerBuilder authManagerBuilder) throws Exception {
		authManagerBuilder.inMemoryAuthentication().withUser("simon").password("1245").roles("ADMIN");
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		   return new BCryptPasswordEncoder();
	}
}
