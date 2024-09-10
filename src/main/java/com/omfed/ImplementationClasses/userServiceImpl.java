package com.omfed.ImplementationClasses;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.omfed.Entities.User;
import com.omfed.Enums.Role;
import com.omfed.Enums.Status;
import com.omfed.Exceptions.UserAlreadyExistException;
import com.omfed.Exceptions.UserNotFoundException;
import com.omfed.Repositories.userRepository;
import com.omfed.Services.userService;

import jakarta.transaction.Transactional;

@Service
public class userServiceImpl implements userService{

	
	@Autowired
    private userRepository usr;
	
	@Override
	@Transactional
	public User createUser(User user) {
		
		 String uid=UUID.randomUUID().toString().replace("-", "").substring(0,7);
		 String prefix="OMFD";
	     String userId=prefix.trim().concat(uid);
	      User findByEmail = usr.findByEmail(user.getEmail());
	      if(findByEmail==null) {
	    	  user.setId(userId);
	    	  return usr.save(user);
	      }else {
	    	  throw new UserAlreadyExistException("User Already Exist...please Create a new user");
	      }	
	}

	@Override
	public List<User> getAllUsers() {
		
		return usr.findAll();
	}

	@Override
	public User getUserById(String id) {
		
		return usr.findById(id).orElseThrow(()->new UserNotFoundException("User with given id is not available "));
	}

	@Override
	public User updateUser(User user, String id) {
		user.setId(id);
		
		return usr.save(user);
	}

	@Override
	public void deleteUser(String id) {
		
		usr.deleteById(id);
	}

	@Override
	public User getUserByEmail(String email) {
		User findByEmail = usr.findByEmail(email);
		return findByEmail;
	}
	
	

}
