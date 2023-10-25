package com.example.demo;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class Surf {
	@Autowired
	JpaService service;
	
	
	@PostMapping("/createUser")
	public ResponseEntity<String> createUser(@PathVariable("firstName") String firstName, @PathVariable("secondName") String secondName
			,@PathVariable("userName") String userName,@PathVariable("Email") String email, @PathVariable("Password") String password){
		
		User user = new User();
		user.setFirstName(firstName);
		user.setLastName(secondName);
		user.setUserName(userName);
		user.setEmail(email);
		user.setPassword(password);
		
		
		VerifyFlag feedback = service.createUser(user);
		
		
		if(feedback == VerifyFlag.USEREXIST) {
			
			return  new ResponseEntity<String>("User Name exists", HttpStatus.NOT_FOUND);
			
			
		} else if(feedback == VerifyFlag.EMAILEXIST) {
			
			
			return new ResponseEntity<String>("Email exists", HttpStatus.NOT_FOUND);
		} else {
			
			
			return new ResponseEntity<String>("user created",HttpStatus.OK);
		}
		
        		
		}
	
	
	@GetMapping("/user/albums")
	public ResponseEntity<ArrayList<Object[]>> getAlbums(@AuthenticationPrincipal User user){
		
		
		return new ResponseEntity<ArrayList<Object[]>>(service.getAlbums(user),HttpStatus.OK);
		
		
	}
	// these method returns photos in a specific album
	@GetMapping("/user/albumPhotos/{album}")
   public ResponseEntity<ArrayList<Object[]>> getAlbums(@AuthenticationPrincipal User user,@PathVariable("album") String albumName ){
		
		
		return new ResponseEntity<ArrayList<Object[]>>(service.getPhotos(user, albumName),HttpStatus.OK);}
	
	
	
	


@GetMapping("/user/picture/{photoid}")
public ResponseEntity<byte []> getSpecificPhoto(@AuthenticationPrincipal User user, @PathVariable("photoid") long photoId){
	
	return  ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(service.getPhotoData(user, photoId));
	
	
}



@PostMapping("/user/upload")
public ResponseEntity<String> uploadPhotos(@AuthenticationPrincipal User user, @PathVariable("albumName") String albumName, @PathVariable("photos") MultipartFile multipart []  ){
	
	ArrayList<Image> imgs = new ArrayList<>();
	
	
	for(MultipartFile x : multipart) {
		
		Image img = new Image();
		img.setAlbum(albumName);
		img.setUser(user);
		try {
			img.setImageData(x.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return  new ResponseEntity<String>("upload failed",HttpStatus.NOT_FOUND);
		} 
		
		imgs.add(img);
		
		
	}
	
	service.saveImages(imgs);
	return new ResponseEntity<String>("Successfull upload",HttpStatus.OK);
	
	
	
}
 @GetMapping("/user/userDetails")
 public ResponseEntity<User> getUserDetails(@AuthenticationPrincipal User user) {
	 
	    return  new ResponseEntity<User>(user,HttpStatus.OK);
	 
	 
	 
 }




	
}



