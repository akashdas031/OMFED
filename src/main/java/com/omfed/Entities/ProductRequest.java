package com.omfed.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.omfed.Enums.RequestStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductRequest {

	@Id
	private String requestId;
	@ManyToOne
	@JoinColumn(name="user_id",nullable=false)
	@JsonIgnore
	private User user;
	@ManyToOne
	@JoinColumn(name="product_id",nullable=false)
	@JsonIgnore
	private Product product;
	private int quantity;
	@Enumerated(EnumType.STRING)
	private RequestStatus status;
	
	
}
