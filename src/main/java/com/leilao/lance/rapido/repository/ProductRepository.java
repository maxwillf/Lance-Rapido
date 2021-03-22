package com.leilao.lance.rapido.repository;

import com.leilao.lance.rapido.model.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
	Product findByIdAndActiveTrue(Integer productId);
    List<Product> findByUserId(Integer userId);
    List<Product> findByBidsUserIdAndActiveTrue(Integer userId);
    List<Product> findByUserIdAndActiveTrue(Integer userId);
    List<Product> findByUserIdAndActiveFalseAndBidsNotNull(Integer userId);
    List<Product> findByActiveTrue();
}
