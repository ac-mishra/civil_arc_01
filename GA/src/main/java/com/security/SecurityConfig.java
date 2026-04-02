package com.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import com.service.CustomUserDetailsService;

@Configuration
public class SecurityConfig {

	@Autowired
	private CustomUserDetailsService userDetailsService;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

	    http
	        .csrf(csrf -> csrf.disable())
	        .userDetailsService(userDetailsService)
	        .authorizeHttpRequests(auth -> auth
	            .requestMatchers("/", "/about", "/contact", "/signup", "/css/**").permitAll()
	            .anyRequest().authenticated()
	        )
	        .formLogin(login -> login
	            .loginPage("/login")
	            .defaultSuccessUrl("/")
	            .permitAll()
	        );

	    return http.build();
	}
}
