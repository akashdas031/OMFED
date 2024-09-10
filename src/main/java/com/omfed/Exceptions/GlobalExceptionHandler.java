package com.omfed.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.omfed.Helper.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ApiResponse> handlerUSerNotFoundException(UserNotFoundException ex) {
		String message=ex.getMessage();
		ApiResponse response = ApiResponse.builder().message(message).status(HttpStatus.NOT_FOUND).isSuccess(false).build();
		return new ResponseEntity<ApiResponse>(response,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(InvalidFileFormatException.class)
	public ResponseEntity<ApiResponse> handlerInvalidFileFormatException(InvalidFileFormatException ex){
		String message=ex.getMessage();
		ApiResponse response = ApiResponse.builder().message(message).status(HttpStatus.BAD_REQUEST).isSuccess(false).build();
		return new ResponseEntity<ApiResponse>(response,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(MilkCollectionDetailsNotFound.class)
	public ResponseEntity<ApiResponse> handlerMilkCollectionDetailsNotFoundException(MilkCollectionDetailsNotFound ex){
		String message=ex.getMessage();
		ApiResponse response = ApiResponse.builder().message(message).status(HttpStatus.NOT_FOUND).isSuccess(false).build();
		return new ResponseEntity<ApiResponse>(response,HttpStatus.NOT_FOUND);
	}
	
	//Exception for Existing user
	
	@ExceptionHandler(UserAlreadyExistException.class)
	public ResponseEntity<ApiResponse> handlerUserAlreadyExistException(UserAlreadyExistException ex){
		String message=ex.getMessage();
		ApiResponse response = ApiResponse.builder().message(message).status(HttpStatus.BAD_REQUEST).isSuccess(false).build();
	    return new ResponseEntity<ApiResponse>(response,HttpStatus.BAD_REQUEST);
		
	}
	
	//image not found exception
	@ExceptionHandler(ImageNotFoundException.class)
	public ResponseEntity<ApiResponse> handlerImageNotFoundException(ImageNotFoundException ex){
		String message=ex.getMessage();
		ApiResponse response = ApiResponse.builder().message(message).status(HttpStatus.NOT_ACCEPTABLE).isSuccess(false).build();
		return new ResponseEntity<ApiResponse>(response,HttpStatus.NOT_ACCEPTABLE);
	}
	
	//Product not found exception
	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<ApiResponse> handlerProductNotFoundException(ProductNotFoundException ex){
		String message=ex.getMessage();
		ApiResponse response = ApiResponse.builder().message(message).status(HttpStatus.NOT_FOUND).isSuccess(false).build();
		return new ResponseEntity<ApiResponse>(response,HttpStatus.NOT_FOUND);
	}
	//Request Not found Exception
	@ExceptionHandler(RequestNotFoundException.class)
	public ResponseEntity<ApiResponse> handlerRequestNotFoundException(RequestNotFoundException ex){
		String message = ex.getMessage();
		ApiResponse response = ApiResponse.builder().message(message).status(HttpStatus.NOT_FOUND).isSuccess(false).build();
		return new ResponseEntity<ApiResponse>(response,HttpStatus.NOT_FOUND);

	}

}
