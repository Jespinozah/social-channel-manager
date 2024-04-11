package com.example.social.manager.controller;

import com.example.social.manager.controller.validation.AuthorizedRoles;
import com.example.social.manager.service.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/user")
public class UserController {

    private record UserRequestLogin(String username, String password) {
    }

    private record UserResponseLogin(String token) {
    }

    private record UserCreateRequest(String email, String firstName, String lastName,
                                     String rol, String username, String password) {
    }

    @Autowired
    private UserServiceInterface userService;

    @PostMapping(path = "/login")
    public @ResponseBody UserResponseLogin login(@RequestBody UserRequestLogin userRequestLogin) {
        return new UserResponseLogin(userService.login(userRequestLogin.username, userRequestLogin.password));
    }

    @AuthorizedRoles({"SUPER_ADMIN"})
    @PostMapping(path = "")
    public @ResponseBody void create(@RequestBody UserCreateRequest userCreateRequest) {
        userService.create(userCreateRequest.email,
                userCreateRequest.firstName,
                userCreateRequest.lastName,
                userCreateRequest.rol,
                userCreateRequest.username,
                userCreateRequest.password
        );
    }

}
