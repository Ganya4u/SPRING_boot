package com.tyss.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserController3 {
	
	@GetMapping("/header")
	public String getHeader(
	        @RequestHeader String token,
	        @RequestHeader String brand) {

	    return "Token: " + token + " Brand: " + brand;
	}

}
