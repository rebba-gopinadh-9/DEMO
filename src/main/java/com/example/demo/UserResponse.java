package com.example.demo;

public class UserResponse {
    private String token;
    private String message;

    // Constructor, getters, and setters
    public UserResponse(String token, String message) {
        this.token = token;
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public String getMessage() {
        return message;
    }
}
