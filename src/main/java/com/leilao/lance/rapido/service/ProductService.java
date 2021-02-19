package com.leilao.lance.rapido.service;

import com.leilao.lance.rapido.model.Product;
import com.leilao.lance.rapido.repository.ProductRepository;
import org.springframework.stereotype.Component;

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

//    public Optional<Product> findProduct(String productname, String id){
//        return Optional.ofNullable(productRepository.findByProductId(productname,id));
//    }


}
