package com.example.springbootcrud.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.springbootcrud.entity.Rol;
import com.example.springbootcrud.entity.User;
import com.example.springbootcrud.repository.RolRepository;
import com.example.springbootcrud.repository.UserRepository;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Transactional
    @Override
    public User save(User user) {

        Optional<Rol> optionalRolUser = rolRepository.findByName("ROLE_USER");

        List<Rol> roles = new ArrayList<>();

        optionalRolUser.ifPresent(roles::add);

        if (user.isAdmin()) {
            Optional<Rol> optionalAdmin = rolRepository.findByName("ROLE_ADMIN");
            optionalAdmin.ifPresent(roles::add);
        }
        user.setRoles(roles);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

}
