package com.omfed.Controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.GetExchange;

import com.omfed.DTOs.MilkCollectionDTO;
import com.omfed.Entities.MilkCollection;
import com.omfed.Entities.User;
import com.omfed.Exceptions.MilkCollectionDetailsNotFound;
import com.omfed.Exceptions.UserNotFoundException;
import com.omfed.Helper.ResponseObj;
import com.omfed.Repositories.userRepository;
import com.omfed.Services.MilkCollectionService;
import com.omfed.Services.userService;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/milkCollectionDEtails/v1/")
public class MilkCollectionController {

	@Autowired
	private MilkCollectionService milkServ;
	
	@Autowired
	private userService userServ;
	
	private Logger logger=LoggerFactory.getLogger(MilkCollectionController.class);
	
	@PostMapping("/collectMilk")
	public ResponseEntity<ResponseObj> collectMilk(@RequestBody MilkCollectionDTO milk){
		
		 ResponseEntity<ResponseObj> res = milkServ.createMilkCollection(milk);
		return res;
	}
	
	@GetMapping("/getMilkCollectionDetails/{id}")
	public ResponseEntity<MilkCollection> getMilkCollectionDetailsUsingMilkId(@PathVariable String id){
		MilkCollection milkCollectionById = milkServ.getMilkCollectionById(id);
		return new ResponseEntity<MilkCollection>(milkCollectionById,HttpStatus.OK);
	}
	
	@GetMapping("/getMilkCollectionDetailsByUserId/{id}")
	public ResponseEntity<List<MilkCollection>> getMilkCollectionByUserId(@PathVariable String id){
		List<User> allUsers = userServ.getAllUsers();
		 List<String> ids = allUsers.stream().filter(t ->t.getId().equals(id)).map(u->u.getId()).collect(Collectors.toList());
		if(!ids.isEmpty()) {
			List<MilkCollection> milkCollectionByUserId = milkServ.getMilkCollectionByUserId(id);
			if(milkCollectionByUserId.isEmpty()) {
				throw new MilkCollectionDetailsNotFound("No collection yet...It seems The user has not contribute any milk...");
			}
			return new ResponseEntity<List<MilkCollection>>(milkCollectionByUserId,HttpStatus.OK);
		}else {
			throw new UserNotFoundException("Invalid user id...Please create user to get collection details");
		}
	}
	@GetMapping
	public ResponseEntity<List<MilkCollection>> getAllCollectionDetails(){
		List<MilkCollection> allMilkCollection = milkServ.getAllMilkCollection();
		return new ResponseEntity<List<MilkCollection>>(allMilkCollection,HttpStatus.OK);
	}
	
}
