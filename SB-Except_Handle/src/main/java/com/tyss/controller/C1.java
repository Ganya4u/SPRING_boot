package com.tyss.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class C1 {

	@GetMapping("/e1")
	public String f1() {

		System.out.println("Got the request");
		int a = 10 / 0;

		return "Thank you for calling";
	}

//	 @ExceptionHandler(ArithmeticException.class)
//	    public String handleArithmeticException(ArithmeticException e) {
//	        return "Arithmetic Exception occurred: " + e.getMessage();
//	    }

	@ExceptionHandler(ArithmeticException.class)
	public ResponseEntity<String> handleArithmeticException(ArithmeticException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@GetMapping("/e2")
	public String f2() {

		String s = null;

		s.length(); // throws NullPointerException

		return "Hello";
	}

	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<String> handleNullPointerException(NullPointerException e) {
		System.out.println("Handled NPE in LOCAL");
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@GetMapping("/e3")
	public String f3() {

		String p = "Ganya";

		System.out.println(p.charAt(10));

		return "Hiii";
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleException(Exception e) {
		System.out.println("exception handled in LOCAL");
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
	}

}
