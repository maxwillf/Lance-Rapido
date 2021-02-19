package com.leilao.lance.rapido.repository;

import com.leilao.lance.rapido.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
    //Product findByProductId(String productname, String id);
    List<Product> findByUserId(Integer userId);
    List<Product> findByBidsUserId(Integer userId);
}
