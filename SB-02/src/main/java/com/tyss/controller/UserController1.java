package com.tyss.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController1 {
	
	
	//http://localhost:8080/users?name=Rahul&age=25
	@GetMapping
    public String getUserByQuery(@RequestParam String name, @RequestParam int age) {
        return "Name: " + name + ", Age: " + age;
    }
	
	
	
	//http://localhost:8080/users/search1
	@GetMapping("/search1")
	public String search1(@RequestParam(required = false) String name) {
	    if(name == null) return "No name provided";
	    return "Searching for " + name;
	}

	@GetMapping("/search2")
	public String search2(@RequestParam(defaultValue = "Unknown") String name) {
	    return "Searching for " + name;
	}
	
	// http://localhost:8080/users/roles?role=admin&role=user
	@GetMapping("/roles")
	public String getRoles(@RequestParam List<String> role) {
	    return "Roles: " + role;
	}

}
