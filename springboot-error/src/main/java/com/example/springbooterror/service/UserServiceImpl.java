package com.example.springbooterror.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.springbooterror.model.User;

@Service
public class UserServiceImpl implements IUserService {

    private List<User> users;

    public UserServiceImpl() {
        this.users = Arrays.asList(new User(1L, "John", "Doe"), new User(2L, "Jane", "Doe"),
                new User(3L, "Alice", "Smith"), new User(4L, "Bob", "Smith"));

    }

    @Override
    public List<User> findAll() {
        return users;
    }

    @Override
    public User findById(Long id) {
        return users.stream().filter(u -> u.getId().equals(id)).findFirst().orElse(null);
    }

}
