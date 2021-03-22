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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
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
			bid.setProduct(product);
			Comment comment = new Comment("essa bicicleta anda??", null, product, user2);
			Comment commentChild = new Comment("e ela vai andar como se n tem pernas??", comment, product, foundUser);
			commentChild.setParent(comment);
			comment.setChildren(new ArrayList<Comment>(Arrays.asList(commentChild)));
			
			Set<Comment> comments = new HashSet<Comment>() {
				{
					add(comment);
				}
			};
			product.setComments(comments);
			System.out.println("DEBUG PRODUCT: " + product.toString());
			productService.saveProduct(product);
			List<Product> queryProducts = productService.findByUserId(foundUser.getId());
			
			if (queryProducts.isEmpty())
				System.out.println("Products not found");
			else
				System.out.println("Products found: " + queryProducts.toString());
			
			List<Product> queryBidsProducts = productService.findActiveBidsByUserId(foundUser.getId());
			if (queryBidsProducts.isEmpty())
				System.out.println("Products with bid not found");
			else
				System.out.println("Active products with bid found: " + queryBidsProducts.toString());
		}

	}
}
