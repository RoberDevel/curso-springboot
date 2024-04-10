package com.example.springbooterror;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.springbooterror.model.User;

@Configuration
public class AppConfiguration {

    @Bean
    List<User> traerUsuarios() {
        List<User> users = new ArrayList<>();
        users = new ArrayList<>();
        users.add(new User(1L, "John", "Doe"));
        users.add(new User(2L, "Jane", "Doe"));
        users.add(new User(3L, "Alice", "Smith"));
        users.add(new User(4L, "Bob", "Smith"));

        return users;
    }

}
