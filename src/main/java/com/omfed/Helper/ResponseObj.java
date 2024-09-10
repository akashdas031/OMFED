package com.omfed.Helper;

import org.springframework.http.HttpStatus;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ResponseObj<T> {
	
	private T obj;
	private String message;
	private HttpStatus status;
	private boolean isSuccess;
	
}
