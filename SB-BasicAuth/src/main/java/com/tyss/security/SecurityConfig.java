package com.tyss.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
	public class SecurityConfig {

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	    return http
	        .csrf(csrf -> csrf.disable())
	        .authorizeHttpRequests(auth -> auth
	            .requestMatchers("/withSecurity").authenticated()
	            .requestMatchers("/noSecurity").permitAll()
	            .anyRequest().denyAll())
	        .formLogin(Customizer.withDefaults())
	        .logout(logout -> logout
	            .logoutUrl("/logout")
	            .logoutSuccessUrl("/noSecurity"))
	        .exceptionHandling(ex -> ex
	                .defaultAuthenticationEntryPointFor(
	                    (req, res, exx) -> res.sendError(403),
	                    request -> !request.getRequestURI().equals("/withSecurity")
	                )
	            )
	        .build();
	}
}
