package com.omfed.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.omfed.Entities.ProductRequest;

public interface ProductRequestRepository extends JpaRepository<ProductRequest, String>{

	List<ProductRequest> findByUserId(String userId);
	
	
}
