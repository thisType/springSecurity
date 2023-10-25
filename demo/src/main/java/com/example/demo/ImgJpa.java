package com.example.demo;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ImgJpa extends JpaRepository<Image, Long> {
	
	    
	 @Query("SELECT DISTINCT ON(image.album) image.id, image.album  FROM  Image image where image.user =:User")
	 ArrayList<Object[]>  findAlbums(@Param("User") User user);  
	 
	 
	 @Query("SELECT image.id, image.album, image.imgName  FROM  Image image where image.user =:User and  image.album =: albumName")
	 ArrayList<Object[]>  findPhotos(@Param("User") User user, @Param("albumName") String albumName); 
	 
	 
	 Optional<Image> findById(Long id);
	 
	 @Query("SELECT img FROM Image img WHERE img.user =: User AND img.id =:imgId")
	 Optional<Image> findUserPhoto(@Param("User") User user, @Param("imgId") Long imgId);
	 
	 
	  
	 

}
