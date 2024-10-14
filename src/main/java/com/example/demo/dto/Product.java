package com.example.demo.dto;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Component
@Entity
public class Product {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	int id ;
	String name ;
	double price ;
	public Product() {
		//
	}
	public Product(int a,String b,double c) {
		this.id = a;
		this.name = b;
		this.price=c;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
}
