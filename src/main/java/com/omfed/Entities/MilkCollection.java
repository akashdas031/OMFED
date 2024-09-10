package com.omfed.Entities;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MilkCollection {

	@Id
	private String id;
	
	@ManyToOne
	@JoinColumn(name="user_id" ,nullable = false)
	@JsonIgnore
	private User user;
	
	private String milkType;
	
	private double amount;
	
	private double pricePerLiter;
	
	private double totalPrice;
	
	private LocalDateTime collectionDate;
}
