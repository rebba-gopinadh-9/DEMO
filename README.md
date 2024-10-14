# This is a Demo project for learning web stack

# We use spring boot project packed with tomcat server from spring website which runs on port 8080

# Am using a maven for dependencies

# It uses RESTful webservices in with spring mvc

# Spring JPA to connect to postgres@14 db running at 5432 , used pgAdmin tool for monitoring the database 

# I am using thymeleaf for server side rendering 

# I am using http only cookies for authentication where the jwt token is sent to browser as a cookie and get verified at each request once

# It has just three pages which will be sent to browser upon request

# /login-page , takes email and password for verification and checks with database of such existance

# it will be routed to /home-page once it is verified along with cookies stored in browser

# it has button which will direct to /product-page when pressed 

# These pages contain simple html and js

# Used postman for verifying the apis mostly
