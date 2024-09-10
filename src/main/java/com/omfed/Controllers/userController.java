package com.omfed.Controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.omfed.Configurations.ImageProperties;
import com.omfed.Entities.User;
import com.omfed.Exceptions.ImageNotFoundException;
import com.omfed.Exceptions.InvalidFileFormatException;
import com.omfed.Exceptions.UserNotFoundException;
import com.omfed.Helper.FileValidator;
import com.omfed.Helper.Message;
import com.omfed.Helper.ResponseMessage;
import com.omfed.Services.userService;


import jakarta.validation.Valid;


@RestController
@RequestMapping("/omfed/users/v1")
@Validated
public class userController {
	
	private Logger logger=LoggerFactory.getLogger(userController.class);
	
	@Autowired
	private userService userServ;

	@Autowired
	private ImageProperties imageProp;
	
	
	
	@PostMapping(value="/addUser",consumes = "multipart/form-data")
	public ResponseEntity<User> createUser(@Valid @RequestPart User user,@RequestPart("file") MultipartFile file) throws IOException{
		
		if(!file.isEmpty()) {
			String profilePic = file.getOriginalFilename();
			File image = new ClassPathResource(imageProp.getUploadDir()).getFile();
			
			Path path = Paths.get(image.getAbsolutePath()+File.separator+file.getOriginalFilename());
			
			Files.copy(file.getInputStream(),path, StandardCopyOption.REPLACE_EXISTING);
			
			user.setProfilePicture(profilePic);
			User createdUser = userServ.createUser(user);
			return new ResponseEntity<User>(createdUser,HttpStatus.CREATED);
		}else {
			throw new ImageNotFoundException("Profile picture is is empty...");
		}
		
		
		
	}
	 
	@GetMapping("/getAllUsers")
	public ResponseEntity<List<User>> getAllUsers() throws IOException {
		List<User> allUsers = userServ.getAllUsers();
		File images = new ClassPathResource(imageProp.getUploadDir()).getFile();
		
		allUsers.stream().forEach(users->{
			
			File file1=new File(images,users.getProfilePicture());
			String absolutePath = file1.getAbsolutePath();
			users.setProfilePicture(absolutePath);
		});
		return new ResponseEntity<List<User>>(allUsers,HttpStatus.OK);
	}
	
	@GetMapping("/userByUID/{Id}")
	public ResponseEntity<User> getUserById(@PathVariable String Id) throws IOException{
		User userById = userServ.getUserById(Id);
		
		File images = new ClassPathResource(imageProp.getUploadDir()).getFile();
		
		File file1=new File(images,userById.getProfilePicture());
		String absolutePath = file1.getAbsolutePath();
		logger.info(""+absolutePath);
	    userById.setProfilePicture(absolutePath);
		return new ResponseEntity<User>(userById,HttpStatus.OK);
	}
	
	@PutMapping(value="/updateUser/{id}",consumes = "multipart/form-data")
	public ResponseEntity<User> updateUser(@Valid @RequestPart User user,@PathVariable String id,@RequestPart("file") MultipartFile file) throws IOException{
		
		User oldUser = userServ.getUserById(id);
		
			
			if(file.isEmpty()) {
				
				user.setProfilePicture(oldUser.getProfilePicture());
				
				
			}else {
				/* delete Old Image from the location */
				
				File images = new ClassPathResource(imageProp.getUploadDir()).getFile();
				
				File file1=new File(images,oldUser.getProfilePicture());
				file1.delete();
						
				
				
                /* update new image */
				
				File image = new ClassPathResource(imageProp.getUploadDir()).getFile();
				
				Path path = Paths.get(image.getAbsolutePath()+File.separator+file.getOriginalFilename());
				
				Files.copy(file.getInputStream(),path, StandardCopyOption.REPLACE_EXISTING);
				
				user.setProfilePicture(file.getOriginalFilename());
				
				
			}
			User updateUser = userServ.updateUser(user, id);
			
	        if(updateUser !=null) {
	        	return new ResponseEntity<User>(user,HttpStatus.OK);
	        }else {
	        	return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
	        }
	}
	
	@DeleteMapping("/deleteUser/{id}")
	public ResponseEntity<User> deleteUser(@PathVariable String id) throws IOException{
		User userById = userServ.getUserById(id);
		if(userById != null) {
            File images = new ClassPathResource(imageProp.getUploadDir()).getFile();
			File file1=new File(images,userById.getProfilePicture());
			file1.delete();
            userServ.deleteUser(id);
            return new ResponseEntity<User>(userById,HttpStatus.OK);
		}else {
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
			}
	}
	
	@GetMapping("/userCount")
	public ResponseEntity<Message> getTotalUserCount(){
		List<User> allUsers = userServ.getAllUsers();
		long count = allUsers.stream().count();
		List<String> l=new ArrayList<>();
		if(count !=0) {
			l.add(Long.toString(count));
			Message res = Message.builder().message("Success").status(HttpStatus.OK).response(l).isSuccess(true).statusCode(HttpStatusCode.valueOf(200)).build();
			return new ResponseEntity<Message>(res,HttpStatus.OK);
		}else {
			throw new UserNotFoundException("No users Exist");
		}
	}
}
