package com.example.springbootaop.controller;

import org.springframework.web.bind.annotation.RestController;
import com.example.springbootaop.service.IByeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class ByeController {

    @Autowired
    private IByeService byeService;

    @GetMapping("/bye")
    public String bye() {
        return byeService.sayGoodbye("Pepe", "hasta pronto!");
    }

}
