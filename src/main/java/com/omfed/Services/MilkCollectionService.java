package com.omfed.Services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.omfed.DTOs.MilkCollectionDTO;
import com.omfed.Entities.MilkCollection;
import com.omfed.Helper.ResponseObj;

public interface MilkCollectionService {

	ResponseEntity<ResponseObj> createMilkCollection(MilkCollectionDTO milk);
	
	MilkCollection getMilkCollectionById(String id);
	
	List<MilkCollection> getMilkCollectionByUserId(String userId);
	
	List<MilkCollection> getAllMilkCollection();
}
