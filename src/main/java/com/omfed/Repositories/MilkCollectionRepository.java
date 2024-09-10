package com.omfed.Repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.omfed.Entities.MilkCollection;


public interface MilkCollectionRepository extends JpaRepository<MilkCollection, String>{

	List<MilkCollection> findByUserId(String userId);
}
