package com.omfed.Controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.catalina.connector.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.omfed.DTOs.ProductRequestDTO;
import com.omfed.DTOs.requestListResponseDTO;
import com.omfed.Entities.Product;
import com.omfed.Entities.ProductRequest;
import com.omfed.Entities.User;
import com.omfed.Enums.RequestStatus;
import com.omfed.Helper.ResponseObj;
import com.omfed.Services.ProductRequestService;
import com.omfed.Services.ProductService;
import com.omfed.Services.userService;


import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/omfed/productRequest/v1/")
public class ProductRequestController {

	private Logger logger =LoggerFactory.getLogger(ProductRequestController.class);
	
	@Autowired
	private userService userServ;
	@Autowired
	private ProductRequestService productRequestServ;

	@Autowired
	private ProductService prodServ;
	
	
	@PostMapping("/createRequest/")
	@Transactional
	public ResponseEntity<requestListResponseDTO> createProductRequest(@RequestBody ProductRequestDTO productReq){
		
		
		ProductRequest requestedProduct = productRequestServ.createProductRequest(productReq);
		
		requestListResponseDTO response = requestListResponseDTO.builder().requestId(requestedProduct.getRequestId())
		                                .user(requestedProduct.getUser())
		                                .product(requestedProduct.getProduct())
		                                .quantity(requestedProduct.getQuantity())
		                                .status(requestedProduct.getStatus())
		                                .build();
		logger.info(""+response);
		return new ResponseEntity<requestListResponseDTO>(response,HttpStatus.CREATED);
	}
	
	@GetMapping("/getSingleRequest/{requestId}")
	public ResponseEntity<ProductRequest> getProductRequestByRequestId(@PathVariable("requestId") String requestId){
		ProductRequest request = productRequestServ.getProductRequestById(requestId);
		
		return new ResponseEntity<ProductRequest>(request,HttpStatus.OK);
	}
	
	@GetMapping("/getAllRequest")
	public ResponseEntity<List<ProductRequest>> getAllRequest(){
		List<ProductRequest> allProductRequests = productRequestServ.getAllProductRequests();
		return new ResponseEntity<List<ProductRequest>>(allProductRequests,HttpStatus.OK);
	}
	@GetMapping("/getRequestByUserId/{userId}")
	public ResponseEntity<List<ProductRequest>> getRequestByUserId(@PathVariable("userId")String userId){
		List<ProductRequest> pRoductRequestByUserId = productRequestServ.getPRoductRequestByUserId(userId);
		return new ResponseEntity<List<ProductRequest>>(pRoductRequestByUserId,HttpStatus.OK);
	}
			
}
