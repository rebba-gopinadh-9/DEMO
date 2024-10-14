package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.JwtUtil;
import com.example.demo.UserResponse;
import com.example.demo.dto.User;
import com.example.demo.service.UserService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

@RestController
public class LoginController {

    @Autowired
    @Qualifier("jwtUtil")
    private JwtUtil jwtUtil;
    
    @Autowired
    @Qualifier("userService")
    private UserService us;

    @PostMapping(value = "/login", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> login(@RequestBody User user, HttpServletResponse response) {
        User u = us.getUserByMail(user.getMail());

        if (u == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new UserResponse("", "No user with that Email!"));
        }

        if (u.getPassword().equals(user.getPassword())) {
            // Generate JWT token
            String token = jwtUtil.generateToken(user.getMail());

            // Set JWT token in an HTTP-only cookie
            Cookie cookie = new Cookie("jwtToken", token);
            cookie.setHttpOnly(true); // Prevent JavaScript access
//            cookie.setSecure(true);   // Set to true if using HTTPS
            cookie.setPath("/");      // The cookie is valid for the entire application
            cookie.setMaxAge(60); // Set cookie expiration time (1 hour)

            // Add the cookie to the response
            response.addCookie(cookie);

            // Return success message
            return ResponseEntity.ok(new UserResponse("", "Login successful!"));
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(new UserResponse("", "Password is wrong"));
        }
    }
}
