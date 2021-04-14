package com.leilao.lance.rapido;

import com.leilao.lance.rapido.model.*;
import com.leilao.lance.rapido.service.ProductService;
import com.leilao.lance.rapido.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
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
		Veiculo veiculoUm = new Veiculo(user, "Celta", 180.00, "2008", "Gasolina comum", "165/70r13", new ProductImage());
		veiculoUm.setImages(
				new HashSet<ProductImage>() {{
					add(new ProductImage());
				}
				});
		Bid bidUm = new Bid(null, user3, 190.00);
		Bid bidDois = new Bid(veiculoUm, user2, 250.00);

		@SuppressWarnings("serial")
		Set<Bid> bidsUm = new HashSet<Bid>() {
			{
				add(bidUm);
				add(bidDois);
			}
		};

		veiculoUm.setBids(bidsUm);
		veiculoUm.setHighestBid(bidDois);
		Product veiculoUmResultado = productService.saveProduct(veiculoUm);
		Set<Bid> resultBids = veiculoUmResultado.getBids();
		Bid bidExtra = new Bid(veiculoUmResultado, user3, 750.00);
		resultBids.add(bidExtra);
		productService.saveProduct(veiculoUmResultado);
		System.out.println("veiculo criado: " + veiculoUm.toString());
		Veiculo veiculoDois = new Veiculo();
		veiculoDois.setInitialBid(5d);
		veiculoDois.setUser(user);
		productService.saveProduct(veiculoDois);
		List<Product> veiculosList = productService.getCatalogByType("veiculo");

		Movel movelUm = new Movel(user2, "sofa-cama", 1200.00, true, "materiais", 50, 30, 180);
		movelUm.setImages(
				new HashSet<ProductImage>() {{
					add(new ProductImage());
				}
				});
		Bid bidTres = new Bid(movelUm, user, 1200.00);
		Bid bidQuatro = new Bid(movelUm, user3, 1300.00);

		@SuppressWarnings("serial")
		Set<Bid> bidsDois = new HashSet<Bid>() {
			{
				add(bidTres);
				add(bidQuatro);
			}
		};

		movelUm.setBids(bidsDois);
		movelUm.setHighestBid(bidQuatro);
		movelUm.setActive(false);
		Product movelRetorno = productService.saveProduct(movelUm);
		System.out.println("movel criado: " + movelUm.toString());

		Optional<User> queryUser = userService.findUser(user.getUsername(), user.getPassword());
		if (queryUser.isEmpty()) {
			System.out.println("User not found");
		} else {
			System.out.println("User found: " + queryUser.get().toString());
			User foundUser = queryUser.get();

			Eletronico eletronicoUm = new Eletronico(foundUser, "notebook", 250.00, 2015, 220, "3 pinos");

			eletronicoUm.setImages(
					new HashSet<ProductImage>() {{
						add(new ProductImage());
					}
					});
			Bid bid = new Bid(eletronicoUm, user2, 250.00);

			@SuppressWarnings("serial")
			Set<Bid> bids = new HashSet<Bid>() {
				{
					add(bid);
				}
			};

			eletronicoUm.setBids(bids);
			eletronicoUm.setHighestBid(bids.iterator().next());
			Product eletronicoResultado = productService.saveProduct(eletronicoUm);
			System.out.println("eletronico criado: " + eletronicoUm.toString());
		}
		List<Product> eletronicoList = productService.getCatalogByType("eletronico");
	}
}
