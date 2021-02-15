package com.leilao.lance.rapido;

import com.leilao.lance.rapido.model.BankInfo;
import com.leilao.lance.rapido.model.User;
import com.leilao.lance.rapido.repository.ProductRepository;
import com.leilao.lance.rapido.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {
    private UserRepository userRepository;
    private ProductRepository productRepository;

    @Autowired
    public DataLoader(UserRepository userRepository, ProductRepository productRepository){
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    public void run(ApplicationArguments args){
        User user = new User(1,"fsadkjf","fsdafas","fsdafds", null);
       userRepository.save(user);
       System.out.println("Created newUser");

      // ));
    }
}
