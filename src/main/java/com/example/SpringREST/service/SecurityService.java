package com.example.SpringREST.service;

public interface SecurityService {
    String findLonggedInUsername();
    void autoLogin(String username, String password);
}