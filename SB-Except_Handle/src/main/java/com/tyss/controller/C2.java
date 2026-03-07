package com.tyss.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class C2 {

	@GetMapping("/e4")
	public String f1() {

		System.out.println("Got the request");
		int a = 10 / 0;

		return "Thank you for calling";
	}

}
