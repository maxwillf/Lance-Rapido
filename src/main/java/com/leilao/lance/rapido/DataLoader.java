package com.leilao.lance.rapido;

import com.leilao.lance.rapido.model.Product;
import com.leilao.lance.rapido.model.User;
import com.leilao.lance.rapido.service.ProductService;
import com.leilao.lance.rapido.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class DataLoader implements ApplicationRunner {
    private UserService userService;
    private ProductService productService;

    @Autowired
    public DataLoader(UserService userService, ProductService productService){
        this.userService = userService;
        this.productService = productService;
    }

    public void run(ApplicationArguments args){
        User user = new User(1,"fsadkjf","fsdafas","fsdafds", null);
       userService.saveUser(user);
       System.out.println("Created newUser");
  Optional<User> queryUser =  userService.findUser(user.getUsername(), user.getPassword());
  if(queryUser.isEmpty()){
      System.out.println("User not found");
  }else {
      System.out.println("User found");
      System.out.println(queryUser.get().toString());

      Product product = new Product();
      User foundUser = queryUser.get();
      product.setUser(foundUser);
      productService.saveProduct(product);
      List<Product> queryProducts = productService.findByUserId(foundUser.getId());
      if(queryProducts.isEmpty()){
          System.out.println("Products not found");
      }
      else {
          System.out.println("Products found: " + queryProducts.toString());

      }
  }

    }
}
