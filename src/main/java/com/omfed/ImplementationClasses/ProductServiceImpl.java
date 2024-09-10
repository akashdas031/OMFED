package com.omfed.ImplementationClasses;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.omfed.Entities.Product;
import com.omfed.Exceptions.ProductNotFoundException;
import com.omfed.Repositories.ProductRepository;
import com.omfed.Services.ProductService;

import jakarta.transaction.Transactional;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductRepository productRepo;
	
	@Override
	@Transactional
	public Product addProduct(Product product) {
		String id = UUID.randomUUID().toString().substring(0,7).replace("-", "");
		String prefix="PID";
		String productId=prefix.concat(id);
		product.setProductId(productId);
		
		return productRepo.save(product);
	}

	@Override
	public List<Product> getAllProducts() {
		List<Product> allProducts = productRepo.findAll();
		return allProducts;
	}

	@Override
	public Product getProductById(String id) {
		Product singleProduct = productRepo.findById(id).orElseThrow(()->new ProductNotFoundException("Product with the id does not exist...try with different id"));
		return singleProduct;
	}

	@Override
	@Transactional
	public Product updateProduct(Product product,String productId) {
		Product availableProduct = productRepo.findById(productId).orElseThrow(()->new ProductNotFoundException("Product with the id does not exist...try with different id"));
		if(availableProduct.getProductId().equalsIgnoreCase(productId)) {
			product.setProductId(productId);
			productRepo.save(product);
		}else {
			throw new ProductNotFoundException("Invalid Product Id...Enter valid product id to update Product");
		}
		
		return product;
	}

	@Override
	public void deleteProduct(String id) {
		Product availableProduct = productRepo.findById(id).orElseThrow(()->new ProductNotFoundException("Product with the id does not exist...try with different id"));
		if(availableProduct.getProductId().equalsIgnoreCase(id)) {
			productRepo.deleteById(id);
		}else {
			throw new ProductNotFoundException("Invalid Product Id...Enter valid product id to Delete Product");
		}
	}

}
