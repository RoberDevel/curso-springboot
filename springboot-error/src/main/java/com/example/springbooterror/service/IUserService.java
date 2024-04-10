package com.example.springbooterror.service;

import java.util.List;

import com.example.springbooterror.model.User;

public interface IUserService {

    public List<User> findAll();

    public User findById(Long id);
}
