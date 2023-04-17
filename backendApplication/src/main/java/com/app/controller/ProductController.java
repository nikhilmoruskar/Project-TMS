package com.app.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.app.pojo.Products;
import com.app.service.IProductsService;

@RestController
@CrossOrigin
@RequestMapping("/products")

public class ProductController {

	@Autowired
	private IProductsService productService;
	 
	
	@GetMapping
	public ResponseEntity<?> fetchAllProducts() {
		
		return new ResponseEntity<>(productService.getAllProducts(),HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<?> saveProducts(@RequestBody Products product)
	{
		System.out.println("in save products " + product.getImages());
		return new ResponseEntity<>(productService.saveProductsDetails(product), HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getProductDetails(@PathVariable int id)
	{
		return new ResponseEntity<>(productService.getProductsDetailsById(id),HttpStatus.OK);
	}
	
	@DeleteMapping("/{productId}")
	public ResponseEntity<?> deleteUSerDetails(@PathVariable int productId)
	{
		System.out.println("in del user details"+productId);
		return new ResponseEntity<>(productService.deleteProduct(productId),HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateProductDetails(@RequestBody Products detachedProduct,@PathVariable int id)
	{
		System.out.println("in update "+detachedProduct+" "+id);
		productService.getProductsDetailsById(id);
		return new ResponseEntity<>(productService.updateProductDetails(detachedProduct),HttpStatus.OK);
	}
	
	
}
