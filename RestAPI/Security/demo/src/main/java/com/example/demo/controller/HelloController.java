package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello() {
        return "Hello, World! This is a secured endpoint.";
    }    
    @GetMapping("/public")
    public String Hi(){
        return "Public web for everybody can acsess";
    }
}
