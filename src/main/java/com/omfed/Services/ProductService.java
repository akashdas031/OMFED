package com.omfed.Services;

import java.util.List;

import com.omfed.Entities.Product;

public interface ProductService {

	Product addProduct(Product product);
	List<Product> getAllProducts();
	Product getProductById(String id);
	Product updateProduct(Product product,String productId);
	void deleteProduct(String id);
}
