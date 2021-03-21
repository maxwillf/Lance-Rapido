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
		User user = new User(1, "fsadkjf", "fsdafas", "fsdafds", null);
		User userTeste = new User(2, "max william", "teste", "deu certo", null);
		userService.saveUser(user);
		userService.saveUser(userTeste);
		System.out.println("Created newUser");
		
		Optional<User> queryUser = userService.findUser(user.getUsername(), user.getPassword());
		if (queryUser.isEmpty()) {
			System.out.println("User not found");
		} else {
			System.out.println("User found");
			System.out.println(queryUser.get().toString());

			Product product = new Product();
			product.setCreationTime(LocalDateTime.now());
			product.setLastUpdateTime(LocalDateTime.now());
			product.setTimeLimit(LocalDateTime.now().plusDays(5));
			product.setActive(true);
			User foundUser = queryUser.get();
			product.setUser(foundUser);
			
			Bid bid = new Bid();
			bid.setUser(foundUser);
			Set<Bid> bids = new HashSet<Bid>() {
				{
					add(bid);
				}
			};
			product.setBids(bids);
			Comment comment = new Comment();
			Comment commentChild = new Comment();
			commentChild.setParent(comment);
//      	comment.setUser(foundUser);
//      	commentChild.setUser(foundUser);
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
