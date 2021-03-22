package com.leilao.lance.rapido;

import com.leilao.lance.rapido.model.Bid;
import com.leilao.lance.rapido.model.Comment;
import com.leilao.lance.rapido.model.Product;
import com.leilao.lance.rapido.model.User;
import com.leilao.lance.rapido.service.ProductService;
import com.leilao.lance.rapido.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.Set;

@Component
public class DataLoader implements ApplicationRunner {
	private UserService userService;
	private ProductService productService;

	@Autowired
	public DataLoader(UserService userService, ProductService productService) {
		this.userService = userService;
		this.productService = productService;
	}

	public void run(ApplicationArguments args) {
		User user = new User(1, "william correa", "senhaqualquer", "william@email.com", null);
		User user2 = new User(2, "max william", "ehumasenha", "max@email.com", null);
		User user3 = new User(3, "michel jean", "senha123", "michel@email.com", null);
		userService.saveUser(user);
		userService.saveUser(user2);
		userService.saveUser(user3);
		System.out.println("Created " + user.getUsername() + ", " + user2.getUsername() + ", " + user3.getUsername());
		
		Product productUm = new Product(user, "skate", 180.00);
		Bid bidUm = new Bid(null, user3, 190.00);
		Bid bidDois = new Bid(null, user2, 250.00);
		
		@SuppressWarnings("serial")
		Set<Bid> bidsUm = new HashSet<Bid>() {
			{
				add(bidUm);
				add(bidDois);
			}
		};
		
		productUm.setBids(bidsUm);
		productUm.setHighestBid(bidDois);
		productService.saveProduct(productUm);
		System.out.println("produto criado: " + productUm.toString());
		
		Product productDois = new Product(user2, "notebook", 1200.00);
		Bid bidTres = new Bid(null, user, 1200.00);
		Bid bidQuatro = new Bid(null, user3, 1300.00);
		
		@SuppressWarnings("serial")
		Set<Bid> bidsDois = new HashSet<Bid>() {
			{
				add(bidTres);
				add(bidQuatro);
			}
		};
		
		productDois.setBids(bidsDois);
		productDois.setHighestBid(bidQuatro);
		productDois.setActive(false);
		productService.saveProduct(productDois);
		System.out.println("produto criado: " + productDois.toString());
		
		Optional<User> queryUser = userService.findUser(user.getUsername(), user.getPassword());
		if (queryUser.isEmpty()) {
			System.out.println("User not found");
		} else {
			System.out.println("User found: " + queryUser.get().toString());
			User foundUser = queryUser.get();

			Product product = new Product(foundUser, "bicicleta", 250.00);
			
			Bid bid = new Bid(null, user2, 250.00);
			
			@SuppressWarnings("serial")
			Set<Bid> bids = new HashSet<Bid>() {
				{
					add(bid);
				}
			};
			
			product.setBids(bids);
			product.setHighestBid(bids.iterator().next());
			productService.saveProduct(product);
			System.out.println("produto criado: " + product.toString());
		}
	}
}
