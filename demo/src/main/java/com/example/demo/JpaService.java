package com.example.demo;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JpaService {
	
	@Autowired
	ImgJpa imgJpa;
	
	
	@Autowired
	UserJpa userJpa;
	
	
	
	
	public  VerifyFlag createUser(User user) {
		 
		Optional<User> userByEmail = userJpa.findByEmail(user.getEmail());
		
		Optional<User>  userByUserName = userJpa.findByUserName(user.getUserName());
		
		if(userByEmail.isPresent()) {
			
			
			
			return VerifyFlag.EMAILEXIST;
		} else if ( userByUserName.isPresent()) {
			
			
			return VerifyFlag.USEREXIST;
		} else {
			userJpa.save(user);
			 return VerifyFlag.CREATED;
			
			
			
		}
		
		
		
	}
	
	public  boolean  saveImages(ArrayList<Image> imgs) {
		
		
		imgJpa.saveAll(imgs);
		return true;
		
		
		
		}
	
	
	public  ArrayList<Object[]> getAlbums(User user){
		
		return imgJpa.findAlbums(user);
		
		
		
	}
	
	public ArrayList<Object[]> getPhotos(User user, String albumName){
		
		return imgJpa.findPhotos(user, albumName);
		
	}
	
	
	public byte[] getPhotoData(User user , Long id) {
		
		Optional<Image> opt = imgJpa.findUserPhoto(user, id);
		
	    if(opt.isEmpty()) {
	    	
	    	
	    	
	    	return null;
	    } else {
	    	
	    	
	    	
	    	Image img = opt.get();
	    	
	    	return img.getImageData();
	    }
		
		
		
	}
	
	
	
	
	
	
	
	 

}
