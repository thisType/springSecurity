package com.example.demo;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserService implements UserDetailsService {
   @Autowired
   UserJpa userJpa;
	
	
	 
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		
	Optional<User> userHolder = userJpa.findByEmail(username);
	
		
		if(userHolder.isPresent()) {
			
			
			
			return userHolder.get(); } else {		
		
	 
		throw new UsernameNotFoundException("user not found");}
}

}