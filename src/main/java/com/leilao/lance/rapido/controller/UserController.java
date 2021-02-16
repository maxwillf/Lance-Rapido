package com.leilao.lance.rapido.controller;

import com.leilao.lance.rapido.model.LoginInfo;
import com.leilao.lance.rapido.model.User;
import com.leilao.lance.rapido.repository.UserRepository;
import com.leilao.lance.rapido.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
    this.userService = userService;
    }

    @PostMapping("/user")
        User newUser(@RequestBody User newUser){
            return userService.saveUser(newUser);
    }

    @PostMapping("/login")
    Optional<User> login(@RequestBody LoginInfo loginInfo){
        return userService.findUser(loginInfo.getUsername(), loginInfo.getPassword());
    }
}
