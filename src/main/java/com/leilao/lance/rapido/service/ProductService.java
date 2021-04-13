package com.leilao.lance.rapido.service;

import com.leilao.lance.rapido.context.ProductContext;
import com.leilao.lance.rapido.model.Bid;
import com.leilao.lance.rapido.model.Comment;
import com.leilao.lance.rapido.model.Eletronico;
import com.leilao.lance.rapido.model.Movel;
import com.leilao.lance.rapido.model.Product;
import com.leilao.lance.rapido.model.Veiculo;
import com.leilao.lance.rapido.repository.BidRepository;
import com.leilao.lance.rapido.repository.ProductRepository;

import com.leilao.lance.rapido.strategy.VeiculoAddBidStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.*;

//TODO: Implementar padrão Strategy para a regra de cada subclasse.
//Veiculo: Diferenca de lances de no minimo R$500, minimamente 3 imagens, campo document necessario.
//Movel: Diferenca de lances de no minimo R$20, minimamente 1 imagem.
//Eletronico: Diferença de lances de no minimo R$10, minimamente 1 imagem, campo voltagem necessario.
// Pode mudar isso a vontade, o importante é que cada regra tenha algo diferente.

@Component
public class ProductService {

	@Autowired
	private final ProductRepository<Product> productRepository;

	@Autowired
    private final BidRepository bidRepository;

	// Da pra retirar os repositorios das subclasses e deixar apenas o productRepository,
	// mas vai ser necessario fazer um cast da subclasse para a classe pai nos metodos.
    public ProductService( ProductRepository<Product> productRepository, BidRepository bidRepository){
        this.productRepository = productRepository;
        this.bidRepository = bidRepository;
    }
    
    // Destrinchar as regras de cada subclasse contida aqui no padrao strategy
    public Product saveProduct(Product product) {
		if(product.getInitialBid() == null || product.getUser() == null){
    		return null;
		}
    	if (productRepository.findByCreationTimeAndUserId(product.getCreationTime(), product.getUser().getId()) == null){
			return productRepository.save(product);
		}
    	return null;
    }

	public List<Product> getFullCatalog(){
		List<Product> products = productRepository.findByActiveTrue();

		if (products.isEmpty()){
			return Collections.emptyList();
		}
		products.removeIf(product -> !updateProductState(product).isActive());
		return products;
	}

	public List<Product> getCatalogByType(Class<?> cls){
    	List<Product> products = productRepository.findByActiveTrue();
		List<Product> filteredProducts = new ArrayList<Product>();

		if (products.isEmpty())
    		return Collections.emptyList();
    	
    	for (Product product : products) {
    		if (product.getClass().equals(cls) &&
    			updateProductState(product).isActive()){
				filteredProducts.add(product);
			}
    	}
        return filteredProducts;
    }

    public Product updateProductState(Product product) {
    	if (LocalDateTime.now().isAfter(product.getTimeLimit())) {
    		if (product.isActive()) {
    			product.setActive(false);
    			productRepository.save(product);
    		}
    	}
    	
    	return product;
    }
    
    public Product addBid(Bid bid) {
    	ProductContext productContext = new ProductContext();
		double bidDifference = 0.00;
		Optional<Product> productOpt = productRepository.findById(bid.getProduct().getId());
        if(productOpt.isEmpty()){
            return null;
        } else {
            Product product = productOpt.get();
            if(product.getInitialBid() > bid.getBidValue()){
            	return null;
			}
            if(!updateProductState(product).isActive())
            	return null;

            Class<?> productType = product.getClass();
            if(productType.equals(Veiculo.class)){
            	productContext.setProductStrategy(new VeiculoAddBidStrategy());
			}
			if(productType.equals(Movel.class)){
				productContext.setProductStrategy(new VeiculoAddBidStrategy());
			}
			if(productType.equals(Eletronico.class)){
				productContext.setProductStrategy(new VeiculoAddBidStrategy());
			}

			Product productResult = productContext.addBid(product,bid);
			if(productResult == null){
				return null;
			}
            productRepository.save(productResult);
            return product;
        }
    }
    
    public Bid findHighestBid(Product product) {
    	if (product.equals(null))
    		return null;
    	Set<Bid> bids = product.getBids();
    	if(bids.isEmpty()){
    		return null;
		}
        Bid highestBid = bids.iterator().next();
    	return highestBid;
    }

	public List<Product> findUserBoughtProductByType(Class<?> cls,  Integer userId) {
		Set<Bid> bids = bidRepository.findByUserId(userId);
		List<Product> boughtProducts = new ArrayList<>();
		for (Bid bid : bids) {
			if (bid.getProduct().getClass().equals(cls)) {
				if (bid.getProduct().getHighestBid().equals(bid))
					boughtProducts.add(bid.getProduct());
			}
		}

		if (boughtProducts.equals(null))
			return Collections.emptyList();
		return boughtProducts;
	}

    public Product saveComment(Comment comment){
        Optional<Product> productOpt = productRepository.findById(comment.getProduct().getId());
        if(productOpt.isEmpty()){
            return null;
        }
        else {
            Product product = productOpt.get();
            if(!updateProductState(product).isActive())
            	return null;
            
            Set<Comment> comments = product.getComments();
            comments.add(comment);
            product.setComments(comments);
            return productRepository.save(product);
        }
    }
    
    public List<Product> findActiveProductsByUserId(Integer userId) {
    	return productRepository.findByUserIdAndActiveTrue(userId);
    }
    
    public List<Product> findSoldProductsByUserId(Integer UsedId) {
    	return productRepository.findByUserIdAndActiveFalseAndBidsNotNull(UsedId);
    }

    public List<Product> findByUserId(Integer userId){
        return productRepository.findByUserId(userId);
    }
    public List<Product> findActiveBidsByUserId(Integer userId){
        return productRepository.findByBidsUserIdAndActiveTrue(userId);
    }
    
    public Product findActiveProductById(Integer productId) {
    	return productRepository.findByIdAndActiveTrue(productId);
    }
}
