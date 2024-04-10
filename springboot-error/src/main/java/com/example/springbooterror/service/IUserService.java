package com.example.springbooterror.service;

import java.util.List;
import java.util.Optional;

import com.example.springbooterror.model.User;

public interface IUserService {

    public List<User> findAll();

    public Optional<User> findById(Long id);
}
