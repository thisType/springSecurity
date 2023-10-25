package com.example.demo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpa extends JpaRepository<User, Long> {
	
	
	
	Optional<User> findByEmail(String email);
	
	
	Optional<User> findByUserName(String userName);
	
	
	
	
	
	    

}
