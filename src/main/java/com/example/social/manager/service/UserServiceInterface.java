package com.example.social.manager.service;

public interface UserServiceInterface {

    String login (String username, String password);

    void create(String email, String firstName, String lastName,
           String rol, String username, String password);
}
