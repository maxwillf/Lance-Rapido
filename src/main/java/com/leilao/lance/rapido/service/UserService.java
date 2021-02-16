package com.leilao.lance.rapido.service;

import com.leilao.lance.rapido.model.User;
import com.leilao.lance.rapido.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
       this.userRepository = userRepository ;
    }

    public User saveUser( User user){
        return userRepository.save(user);
    }

    public Optional<User> findUser(String username, String password){
       return Optional.ofNullable(userRepository.findByUsernameAndPassword(username,password));
    }
}
