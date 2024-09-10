package com.omfed.Exceptions;

public class InvalidFileFormatException extends RuntimeException{

	public InvalidFileFormatException() {
		super("File format is not supported...Please enter the PNG , JPG or JPEG image");
	}
	
	public InvalidFileFormatException(String message) {
		super(message);
	}
}
