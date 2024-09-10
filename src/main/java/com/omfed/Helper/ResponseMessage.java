package com.omfed.Helper;

import org.springframework.http.HttpStatus;

import com.omfed.Entities.User;

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
public class ResponseMessage {

	private String message;
	private User users;
	private String imageName;
	private byte[] image;
	private HttpStatus status;
}
