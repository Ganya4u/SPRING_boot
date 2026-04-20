package com.tyss.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tyss.dto.LoginDTO;
import com.tyss.entity.User;
import com.tyss.repo.UserRepo;
import com.tyss.service.JwtService;

@RestController
public class AuthController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserRepo repo;

    @Autowired
    private PasswordEncoder encoder;

    @PostMapping("/register")
    public String register(@RequestBody User user) {

        user.setPassword(encoder.encode(user.getPassword())); // IMPORTANT
        repo.save(user);

        return "Registered";
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginDTO dto) {

        manager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword())
        );

        return jwtService.generateToken(dto.getEmail());
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello secured API ✅";
    }
}