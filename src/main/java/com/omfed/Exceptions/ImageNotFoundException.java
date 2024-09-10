package com.omfed.Exceptions;

public class ImageNotFoundException extends RuntimeException{

	public ImageNotFoundException() {
		super("Image Field is Empty...Choose Image Before Proceed...");
	}
	
	public ImageNotFoundException(String message) {
		super(message);
	}
}
