package com.example.springbooterror.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springbooterror.exceptions.UserNotFoundException;
import com.example.springbooterror.model.User;
import com.example.springbooterror.service.IUserService;

@RestController
@RequestMapping("/app")
public class AppController {

    @Autowired
    private IUserService userService;

    @GetMapping
    public String index() {

        // int value = 10 / 0;
        int value2 = Integer.parseInt("10a");
        return "ok 200";
    }

    @GetMapping("/show/{id}")
    public User show(@PathVariable Long id) {

        User user = userService.findById(id).orElseThrow(() -> new UserNotFoundException("El usuario no existe"));
        /*
         * Modificar User por ResponseEntity<User> y retornar
         * ResponseEntity.ok(optionalUser)
         * Optional<User> optionalUser = userService.findById(id);
         * if (optionalUser.isEmpty()) {
         * return ResponseEntity.notFound().build();
         * }
         */
        System.out.println(user.getLastname());
        return user;

    }

}
