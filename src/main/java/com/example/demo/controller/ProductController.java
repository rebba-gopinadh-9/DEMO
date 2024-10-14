package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.Product;
import com.example.demo.service.ProductService;

@RestController
public class ProductController {
	@Autowired
	@Qualifier("productService")
	ProductService ps ;
	
	@GetMapping("/products")
	public ResponseEntity<List<Product>> getAllProducts(){
		return ps.getAllProducts();
	}
	
	@RequestMapping("/products/{id1}")
	public ResponseEntity<Product> getProductById(@PathVariable("id1") int id1){
		return ps.getProductById(id1);
	}
	
	@PostMapping("/products")
	public void addProduct( @RequestBody Product p) {
		ps.addProduct(p);
	}
	
}
