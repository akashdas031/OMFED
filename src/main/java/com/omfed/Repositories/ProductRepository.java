package com.omfed.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.omfed.Entities.Product;

public interface ProductRepository extends JpaRepository<Product, String>{

}
