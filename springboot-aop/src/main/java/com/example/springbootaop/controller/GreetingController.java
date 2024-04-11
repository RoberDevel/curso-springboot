package com.example.springbootaop.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.springbootaop.service.IGreetingService;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class GreetingController {

    @Autowired
    private IGreetingService greetingService;

    @GetMapping("/greeting")
    public ResponseEntity<?> greeting() {
        return ResponseEntity
                .ok(Collections.singletonMap("greeting", greetingService.sayHello("Pepe", "como estás?")));
    }

    @GetMapping("/greeting-error")
    public ResponseEntity<?> greetingError() {
        return ResponseEntity
                .ok(Collections.singletonMap("greeting", greetingService.sayHelloError("Pepe", "como estás?")));
    }
}
