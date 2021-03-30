package com.example.SpringREST.service.impl;

import com.example.SpringREST.service.UserService;
import org.springframework.stereotype.Service;


import com.example.SpringREST.model.User;
import com.example.SpringREST.repository.RoleRespository;
import com.example.SpringREST.repository.UserRepository;
import com.example.SpringREST.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
@Service
public class UserServicempl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRespository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(new HashSet<>(roleRepository.findAll()));
        userRepository.save(user);

    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }





}
