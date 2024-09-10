package com.omfed.Exceptions;

public class ProductNotFoundException extends RuntimeException{
	
	public ProductNotFoundException() {
		super("Product with given Id is not available in the server");
	}
	
	public ProductNotFoundException(String message){
		super(message);
	}

}
