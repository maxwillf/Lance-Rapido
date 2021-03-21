package com.leilao.lance.rapido.service;

import com.leilao.lance.rapido.model.Bid;
import com.leilao.lance.rapido.model.Comment;
import com.leilao.lance.rapido.model.Product;
import com.leilao.lance.rapido.repository.ProductRepository;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Component
@Transactional
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository ;
    }

    public Product saveProduct(Product product){
        return productRepository.save(product);
    }

    public List<Product> getCatalog(){
    	List<Product> products = productRepository.findByActiveTrue();
    	
    	if (products.isEmpty())
    		return Collections.emptyList();
    	
    	for (Product product : products) {
    		if (!updateProductState(product).isActive())
    			products.remove(product);
    	}
    	
        return products;
    }
    
    public Product updateProductState(Product product) {
    	if (LocalDateTime.now().isAfter(product.getTimeLimit())) {
    		if (product.isActive()) {
    			product.setActive(false);
    			saveProduct(product);
    		}
    	}
    	
    	return product;
    }

    public Product saveBid(Bid bid){
        Optional<Product> productOpt = productRepository.findById(bid.getProduct().getId());
        if(productOpt.isEmpty()){
            return null;
        } else {
            Product product = productOpt.get();
            if(!updateProductState(product).isActive())
            	return null;
            
            Set<Bid> bids = product.getBids();
            bids.add(bid);
            product.setBids(bids);
            return productRepository.save(product);
        }
    }
    
    public double getHighestBid(Product product) {
    	double max = 0.0;
    	for (Bid bid: product.getBids()) {
    		if (bid.getBidValue() > max)
    			max = bid.getBidValue();
    	}
    	
    	return max;
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
}
