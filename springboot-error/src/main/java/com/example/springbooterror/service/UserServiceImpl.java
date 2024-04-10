package com.example.springbooterror.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springbooterror.model.Role;
import com.example.springbooterror.model.User;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private List<User> users;

    /*
     * public UserServiceImpl() {
     * 
     * this.users = new ArrayList<>();
     * this.users.add(new User(1L, "John", "Doe"));
     * this.users.add(new User(2L, "Jane", "Doe"));
     * this.users.add(new User(3L, "Alice", "Smith"));
     * this.users.add(new User(4L, "Bob", "Smith"));
     * 
     * }
     */

    @Override
    public List<User> findAll() {
        return users;
    }

    @Override
    public Optional<User> findById(Long id) {

        return users.stream().filter(u -> u.getId().equals(id)).findFirst();
        /*
         * for (User u : users) {
         * if (u.getId().equals(id)) {
         * user = u;
         * break;
         * }
         * }
         */

        // return Optional.ofNullable(user);

        /*
         * return users.stream().filter(u -> {
         * System.out.println(u.getLastname());
         * return u.getId().equals(id);
         * }).findFirst().orElse(null) ;
         */
    }

}
