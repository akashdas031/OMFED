package com.omfed.Helper;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Message {

	private String message;
	private HttpStatus status;
	private List<String> response;
	private HttpStatusCode statusCode;
	private boolean isSuccess;
}
