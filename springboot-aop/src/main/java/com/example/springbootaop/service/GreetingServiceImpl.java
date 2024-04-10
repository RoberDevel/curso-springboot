package com.example.springbootaop.service;

import org.springframework.stereotype.Service;

@Service
public class GreetingServiceImpl implements IGreetingService {

    @Override
    public String sayHello(String person, String message) {
        String greeting = "Hola " + person + ", " + message;
        System.out.println(greeting);
        return greeting;
    }

}
