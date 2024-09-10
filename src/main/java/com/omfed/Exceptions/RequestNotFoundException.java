package com.omfed.Exceptions;

public class RequestNotFoundException extends RuntimeException{

	public RequestNotFoundException(){
		super("Request Not found");
	}
	public RequestNotFoundException(String message) {
		super(message);
	}
}
