package com.leilao.lance.rapido.repository;

import com.leilao.lance.rapido.model.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
    List<Product> findByUserId(Integer userId);
    List<Product> findByBidsUserIdAndActiveTrue(Integer userId);
    List<Product> findByUserIdAndActiveTrue(Integer userId);
    List<Product> findByUserIdAndActiveFalseAndBidsNotNull(Integer userId);
    List<Product> findByActiveTrue();
    
    /*@Query("SELECT * FROM Produto JOIN Bid ON bid.product_id = product.id WHERE")
    List<Product> findByUserId*/
}
