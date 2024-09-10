package com.omfed.Services;

import java.util.List;

import com.omfed.DTOs.ProductRequestDTO;
import com.omfed.Entities.ProductRequest;

public interface ProductRequestService {

	ProductRequest createProductRequest(ProductRequestDTO productRequest);
	List<ProductRequest> getAllProductRequests();
	ProductRequest getProductRequestById(String id);
	List<ProductRequest> getPRoductRequestByUserId(String userId);
}
