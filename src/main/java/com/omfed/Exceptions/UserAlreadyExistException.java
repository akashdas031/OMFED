package com.omfed.Exceptions;

public class UserAlreadyExistException extends RuntimeException{
	
	public UserAlreadyExistException() {
		super("User Email already exist...Please Create a user with different Email");
	}
	
	public UserAlreadyExistException(String message){
		super(message);
	}

}
