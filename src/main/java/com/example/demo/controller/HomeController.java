package com.example.demo.controller;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Controller
public class HomeController {
	final private static SecretKey secret = Keys.secretKeyFor(SignatureAlgorithm.HS256);
	public static SecretKey getSecKey() {
		return HomeController.secret;
	}

    @GetMapping("/home-page")
    public String homePage(Model model) {
    	System.out.println("########");
    	model.getAttribute("");
        // Add attributes to the model that can be used in the view
        return "home";  // This corresponds to home.html (Thymeleaf template)
    }
    @GetMapping("/login-page")
    public String loginPage(Model model) {
        // Add attributes to the model that can be used in the view
        return "login";  // This corresponds to home.html (Thymeleaf template)
    }
    @GetMapping("/products-page")
    public String productPage() {
        // Add attributes to the model that can be used in the view
        return "products";  // This corresponds to home.html (Thymeleaf template)
    }
}
