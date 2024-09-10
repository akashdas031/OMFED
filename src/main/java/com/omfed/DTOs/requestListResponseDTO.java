package com.omfed.DTOs;

import com.omfed.Entities.Product;
import com.omfed.Entities.User;
import com.omfed.Enums.RequestStatus;

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
public class requestListResponseDTO {

	private String requestId;
	private User user;
	private Product product;
	private int quantity;
	private RequestStatus status;
}
