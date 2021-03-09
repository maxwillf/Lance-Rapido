package com.leilao.lance.rapido.service;

import com.leilao.lance.rapido.model.Bid;
import com.leilao.lance.rapido.model.Comment;
import com.leilao.lance.rapido.model.Product;
import com.leilao.lance.rapido.repository.ProductRepository;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
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
        return productRepository.findAll();
    }

    public Product saveBid(Bid bid){
        Optional<Product> productOpt = productRepository.findById(bid.getProduct().getId());
        if(productOpt.isEmpty()){
            return null;
        }
        else {
            Product product = productOpt.get();
            Set<Bid> bids = product.getBids();
            bids.add(bid);
            product.setBids(bids);
            return productRepository.save(product);
        }
    }

    public Product saveComment(Comment comment){
        Optional<Product> productOpt = productRepository.findById(comment.getProduct().getId());
        if(productOpt.isEmpty()){
            return null;
        }
        else {
            Product product = productOpt.get();
            Set<Comment> comments = product.getComments();
            comments.add(comment);
            product.setComments(comments);
            return productRepository.save(product);
        }
    }

    public List<Product> findByUserId(Integer userId){
        return productRepository.findByUserId(userId);
    }
    public List<Product> findByBidsUserId(Integer userId){
        return productRepository.findByBidsUserId(userId);
    }
}
