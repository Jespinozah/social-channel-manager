package com.example.social.manager.service;

import com.example.social.manager.domain.User;
import com.example.social.manager.repository.UserRepository;
import com.example.social.manager.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService implements UserServiceInterface{

    @Autowired
    private UserRepository userRepository;

    @Override
    public String login(String username, String password) {

        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            var dbUser = user.get();
            if (dbUser.getPassword().equals(password)) {
                return JwtUtil.generateToken(
                        UUID.randomUUID().toString(),
                        new JwtUtil.User(dbUser.getUsername(),dbUser.getEmail(), dbUser.getRole()));
            }

        }
        return "";
    }

    @Override
    public Integer create(String email, String firstName, String lastName, String rol, String username, String password) {
       var user = new User();
       user.setUsername(username);
       user.setFirstName(firstName);
       user.setLastName(lastName);
       user.setEmail(email);
       user.setRole(rol);
       user.setPassword(password);
       return userRepository.save(user).getId();
    }
}
