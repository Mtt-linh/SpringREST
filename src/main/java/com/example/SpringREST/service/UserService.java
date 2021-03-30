package com.example.SpringREST.service;
import com.example.SpringREST.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService  {
    void save(User user);
    User findByUsername(String username);
}