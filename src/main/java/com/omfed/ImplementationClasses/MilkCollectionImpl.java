package com.omfed.ImplementationClasses;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.omfed.DTOs.MilkCollectionDTO;
import com.omfed.Entities.MilkCollection;
import com.omfed.Entities.User;
import com.omfed.Exceptions.MilkCollectionDetailsNotFound;
import com.omfed.Exceptions.UserNotFoundException;
import com.omfed.Helper.ApiResponse;
import com.omfed.Helper.ResponseObj;
import com.omfed.Repositories.MilkCollectionRepository;
import com.omfed.Repositories.userRepository;
import com.omfed.Services.MilkCollectionService;

import jakarta.transaction.Transactional;

@Service
public class MilkCollectionImpl implements MilkCollectionService{

	@Autowired
	private MilkCollectionRepository milkRepo;
	
	@Autowired
	private userRepository userRepo;
	private Logger logger=LoggerFactory.getLogger(MilkCollectionService.class);
	
	@Override
	@Transactional
	public ResponseEntity<ResponseObj> createMilkCollection(MilkCollectionDTO milk) {
		double totalPrice=
				milk.getAmount()*milk.getPricePerLiter();
		milk.setTotalPrice(totalPrice);
		LocalDateTime date=LocalDateTime.now();
		milk.setCollectionDate(date);
		String userId=milk.getUserId();
		if(userId !=null) {
		logger.info("user id in the service imple" + userId);
		
		Optional<User> userOpt = userRepo.findById(userId);
		 logger.info("user which is getting from db"+userOpt.toString());
		 User user = userOpt.get();
		 String generatedId=UUID.randomUUID().toString().replace("-", "").substring(0,8);
		 String prefix="ML";
	     String milkId=prefix.trim().concat(generatedId);
		 logger.info("user which is getting from db"+user.toString());
		MilkCollection om = MilkCollection.builder().id(milkId).milkType(milk.getMilkType()).amount(milk.getAmount()).pricePerLiter(milk.getPricePerLiter())
					.totalPrice(totalPrice).collectionDate(date).user(user).build();
		MilkCollection response = milkRepo.save(om);
		ResponseObj<Object> val = ResponseObj.builder().obj(response).message("Success").status(HttpStatus.CREATED).isSuccess(true).build();
		return new ResponseEntity<ResponseObj>(val,HttpStatus.CREATED);
		 }else {
			 ResponseObj<Object> err = ResponseObj.builder().obj(null).status(HttpStatus.NOT_FOUND).message("User with given id Not exist..Create user to collect milk").isSuccess(false).build();
			 return new ResponseEntity<ResponseObj>(err,HttpStatus.NOT_FOUND);
		 }
		
		
	}

	@Override
	public MilkCollection getMilkCollectionById(String id) {
		
		return milkRepo.findById(id).orElseThrow(()->new MilkCollectionDetailsNotFound("Milk Collection Record not Available..."));
	}

	@Override
	public List<MilkCollection> getMilkCollectionByUserId(String userId) {
		
		Optional<User> userOpt = userRepo.findById(userId);
		if(userOpt.isPresent()) {
			return milkRepo.findByUserId(userId);
		}
		
		return null;
	}

	@Override
	public List<MilkCollection> getAllMilkCollection() {
		
		return milkRepo.findAll();
	}

}
