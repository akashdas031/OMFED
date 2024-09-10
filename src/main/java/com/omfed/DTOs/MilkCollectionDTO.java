package com.omfed.DTOs;

import java.time.LocalDateTime;

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
public class MilkCollectionDTO {

	private String userId;
	private String milkType;
	private double amount;
	private double pricePerLiter;
	private double totalPrice;
	private LocalDateTime collectionDate;
}
