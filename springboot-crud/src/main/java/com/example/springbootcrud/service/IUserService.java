package com.example.springbootcrud.service;

import java.util.List;

import com.example.springbootcrud.entity.User;

public interface IUserService {

    List<User> findAll();

    User save(User user);

    boolean existsByUsername(String username);
}
