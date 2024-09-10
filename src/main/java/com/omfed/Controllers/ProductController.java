package com.omfed.Controllers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.omfed.Entities.Product;
import com.omfed.Helper.Message;
import com.omfed.Services.ProductService;

@RestController
@RequestMapping("/omfed/product/v1/")
public class ProductController {

	@Autowired
	private ProductService productServ;
	
	private Logger logger=LoggerFactory.getLogger(ProductController.class);
	
	@PostMapping("/createProduct")
	public ResponseEntity<Product> createProduct(@RequestBody Product product){
		Product createdProduct = productServ.addProduct(product);
		return new ResponseEntity<Product>(createdProduct,HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<Product>> getAllProducts(){
		List<Product> allProducts = productServ.getAllProducts();
		return new ResponseEntity<List<Product>>(allProducts,HttpStatus.OK);
	}
	@GetMapping("/getSingleProduct/{productId}")
	public ResponseEntity<Product> getSingleProduct(@PathVariable("productId") String productId){
		Product singleProduct = productServ.getProductById(productId);
		return new ResponseEntity<Product>(singleProduct,HttpStatus.OK);
	}
	
	@PutMapping("/updateProduct/{productId}")
	public ResponseEntity<Product> updateProduct(@RequestBody Product product,@PathVariable("productId") String productId){
		Product updatedProduct = productServ.updateProduct(product, productId);
		return new ResponseEntity<Product>(updatedProduct,HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteProduct/{productId}")
	public ResponseEntity<Message> deleteProduct(@PathVariable("productId") String productId){
		
		productServ.deleteProduct(productId);
		Message res = Message.builder().message("Product with id"+productId+" has been Deleted Successfullt").status(HttpStatus.OK).response(new ArrayList<>()).isSuccess(true).build();
		return new ResponseEntity<Message>(res,HttpStatus.OK);
	}
}
