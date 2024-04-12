package com.example.social.manager.service;

import com.example.social.manager.domain.User;
import com.example.social.manager.exception.UnauthorizedException;
import com.example.social.manager.repository.UserRepository;
import com.example.social.manager.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService implements UserServiceInterface {

    @Autowired
    private UserRepository userRepository;

    @Override
    public String login(String username, String password) {

        User user = userRepository.findByUsername(username).orElseThrow(() -> new UnauthorizedException("username or " +
                "password not found"));

        if (user.getPassword().equals(password)) {
            return JwtUtil.generateToken(
                    UUID.randomUUID().toString(),
                    new JwtUtil.User(user.getUsername(), user.getEmail(), user.getRole()));
        } else {
            throw new UnauthorizedException(" username or password not found");
        }

    }

    @Override
    public Integer create(String email, String firstName, String lastName, String rol, String username, String
            password) {
        var user = new User();
        user.setUsername(username);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setRole(rol);
        user.setPassword(password);
        return userRepository.save(user).getId();
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }
}
