package com.example.social.manager.service;

import com.example.social.manager.domain.User;
import com.example.social.manager.repository.UserRepository;
import com.example.social.manager.util.JWT;
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
        if (user.isPresent() && user.get().getPassword().equals(password)) {
            var dbUser = user.get();
            if (dbUser.getPassword().equals(password)) {
                return JWT.createJWT(
                        UUID.randomUUID().toString(),
                        new JWT.User(dbUser.getUsername(),dbUser.getEmail(), dbUser.getRole()),
                        1200L );
            }

        }
        return "";
    }
}
