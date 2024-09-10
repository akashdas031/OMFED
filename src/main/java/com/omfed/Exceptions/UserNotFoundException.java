package com.omfed.Exceptions;

public class UserNotFoundException extends RuntimeException{

 public	UserNotFoundException(){
		super("User with given id is not available");
	}
	
	public UserNotFoundException(String message){
		super(message);
	}
}
