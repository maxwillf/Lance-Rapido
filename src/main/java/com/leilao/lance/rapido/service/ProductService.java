package com.leilao.lance.rapido.service;

import com.leilao.lance.rapido.model.Product;
import com.leilao.lance.rapido.repository.ProductRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository ;
    }

    public Product saveProduct(Product product){
        return productRepository.save(product);
    }

    public List<Product> findByUserId(Integer userId){
        return productRepository.findByUserId(userId);
    }
    public List<Product> findByBidsUserId(Integer userId){
        return productRepository.findByBidsUserId(userId);
    }
}
