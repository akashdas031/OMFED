package com.omfed.Exceptions;

public class MilkCollectionDetailsNotFound extends RuntimeException{

	public MilkCollectionDetailsNotFound(){
		super("Milk collection details with given id is not available...");
	}
	
	public MilkCollectionDetailsNotFound(String message){
		super(message);
	}
}
