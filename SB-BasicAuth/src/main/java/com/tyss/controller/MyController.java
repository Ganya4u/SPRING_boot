package com.tyss.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    @GetMapping("/withSecurity")
    public String secured() {
        return "This is a secured endpoint. You are logged in!";
    }

    @GetMapping("/noSecurity")
    public String open() {
        return "This is an open endpoint. No login required!";
    }
}
