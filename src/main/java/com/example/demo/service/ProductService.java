package com.example.demo.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.dto.Product;
import com.example.demo.repo.ProductRepo;

@Service
public class ProductService {
	
	@Autowired(required=true)
	@Qualifier("productRepo")
	ProductRepo repo ;
	
	public ResponseEntity<List<Product>>  getAllProducts(){
		List<Product> list = repo.findAll();
		if (list.size()>0) {
			return new ResponseEntity<List<Product>>(list, HttpStatus.OK);			
		}
		return new ResponseEntity<List<Product>>(HttpStatus.NO_CONTENT);
	}
	
	public ResponseEntity<Product> getProductById(int id) {
		Product p = repo.findById(id).orElse(null);
		if (p==null) {
			return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Product>(p,HttpStatus.OK);
	}
	
	public void addProduct(Product p) {
		repo.save(p);
	}
	
	public void deleteProduct(Product p) {
		repo.delete(p);
	}
	
	public void updateProduct(Product p) {
		repo.save(p);
	}
}
