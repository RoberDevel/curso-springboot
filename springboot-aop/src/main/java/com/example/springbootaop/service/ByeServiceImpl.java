package com.example.springbootaop.service;

import org.springframework.stereotype.Service;

@Service
public class ByeServiceImpl implements IByeService {

    @Override
    public String sayGoodbye(String person, String message) {
        String goodbye = "Goodbye " + person + ", " + message;
        System.out.println(goodbye);
        return goodbye;
    }

}
