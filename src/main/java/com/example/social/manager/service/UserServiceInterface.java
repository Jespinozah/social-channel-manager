package com.example.social.manager.service;

import com.example.social.manager.domain.User;

import java.util.List;

public interface UserServiceInterface {

    String login (String username, String password);

    Integer create(String email, String firstName, String lastName,
           String rol, String username, String password);

    List<User> getAll();
}
