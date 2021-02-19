package com.leilao.lance.rapido.repository;

import com.leilao.lance.rapido.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
    //Product findByProductId(String productname, String id);
}
