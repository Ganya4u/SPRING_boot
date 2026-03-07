package com.tyss.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController2 {

    // http://localhost:8080/users/10
    @GetMapping("/{id}")
    public String getUser1(@PathVariable int id) {
        return "User ID: " + id;
    }

    // http://localhost:8080/users/10/Rahul
    @GetMapping("/{id}/{name}")
    public String getUser2(@PathVariable int id, @PathVariable String name) {
        return "User ID: " + id + " Name: " + name;
    }

    // http://localhost:8080/users/10/role?role=admin
    @GetMapping("/{id}/role")
    public String getUser3(@PathVariable int id, @RequestParam String role) {
        return "User ID: " + id + " Role: " + role;
    }
}