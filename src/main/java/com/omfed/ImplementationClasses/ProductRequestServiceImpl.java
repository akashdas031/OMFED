package com.omfed.ImplementationClasses;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.omfed.DTOs.ProductRequestDTO;
import com.omfed.Entities.Product;
import com.omfed.Entities.ProductRequest;
import com.omfed.Entities.User;
import com.omfed.Enums.RequestStatus;
import com.omfed.Exceptions.ProductNotFoundException;
import com.omfed.Exceptions.RequestNotFoundException;
import com.omfed.Repositories.ProductRequestRepository;
import com.omfed.Repositories.userRepository;
import com.omfed.Services.ProductRequestService;
import com.omfed.Services.ProductService;
import com.omfed.Services.userService;

@Service
public class ProductRequestServiceImpl implements ProductRequestService{

	
	@Autowired
	private ProductRequestRepository productRequestRepo;
	
	@Autowired
	private userService userServ;
	
	@Autowired
	private ProductService prodServ;
	
	private Logger logger=LoggerFactory.getLogger(ProductRequestService.class);
	
	private boolean checkValidRequest(String userId,String productId) {
		List<ProductRequest> findAll = productRequestRepo.findAll();
		boolean res=false;
		for(ProductRequest req:findAll) {
			String pid=req.getProduct().getProductId();
			String uid=req.getUser().getId();
			if(uid.equals(userId) && pid.equals(productId)) {
				res= false;
			}else {
				res= true;
			}
		}
		return res;
	}
	

	@Override
	public ProductRequest createProductRequest(ProductRequestDTO request) {
		 
		     User user = userServ.getUserById(request.getUserId());
			 Product product = prodServ.getProductById(request.getProductId());
			 boolean validate = checkValidRequest(request.getUserId(), request.getProductId());
			 logger.info("Valid request or not : "+validate);
			 if(checkValidRequest(request.getUserId(),request.getProductId())) {
				 String gid = UUID.randomUUID().toString().replace("-", "").substring(0,5);
				 String prefix="REQ";
				 String requestId=prefix.concat(gid).trim();
					 ProductRequest res = ProductRequest.builder().requestId(requestId)
	                         .user(user)
	                         .product(product)
	                         .quantity(request.getQuantity())
	                         .status(RequestStatus.REQUESTED)
	                         .build();
					 ProductRequest resp = productRequestRepo.save(res);
					logger.info(""+resp);
					 return res;				 
			 }else {
				 throw new RequestNotFoundException("You have already Request for the product please wait untill we process it.");
			 }
			 
			 
			  
		   
	}

	@Override
	public List<ProductRequest> getAllProductRequests() {
		
		return productRequestRepo.findAll();
	}

	@Override
	public ProductRequest getProductRequestById(String id) {
		ProductRequest req = productRequestRepo.findById(id).orElseThrow(()-> new RequestNotFoundException("You have not request for any item yet..."));
		logger.info(""+req.getProduct());
		return req;
	}

	@Override
	public List<ProductRequest> getPRoductRequestByUserId(String userId) {
		List<ProductRequest> req = productRequestRepo.findByUserId(userId);
		return req;
	}
	

}
