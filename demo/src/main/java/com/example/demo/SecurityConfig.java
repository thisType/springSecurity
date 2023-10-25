package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	
	     @Autowired
	     CustomUserService customUserService;
	     
	     
	     
	     @Bean
	     SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	    	 
	    	 
	    	 
	    	 return http
	    			 .csrf(csrf -> csrf.disable()).authorizeHttpRequests((authz) -> authz
                     .requestMatchers("/user/**").hasRole("USER").anyRequest().permitAll()
             ).formLogin(formLogin -> formLogin
                     .loginPage("/loginUser").successForwardUrl("/Photos")).logout(logout -> logout
                             .logoutUrl("/user/logoutUser")  // specify your custom logout URL here
                             .logoutSuccessUrl("/pCloud")  // specify the URL to redirect to after logout
                         ).userDetailsService(customUserService)
	    			 .build();
	    	 
	    	 
	    	 }

}
