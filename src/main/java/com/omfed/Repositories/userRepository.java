package com.omfed.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.omfed.Entities.User;

@Repository
public interface userRepository extends JpaRepository<User, String>{

	User findByEmail(String email);
}
