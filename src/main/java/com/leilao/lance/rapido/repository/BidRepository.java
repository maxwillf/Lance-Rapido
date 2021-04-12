package com.leilao.lance.rapido.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.leilao.lance.rapido.model.Bid;
import com.leilao.lance.rapido.model.Product;

@Repository
public interface BidRepository extends JpaRepository<Bid, Integer> {
	Set<Bid> findByUserId(Integer userId);
	Set<Bid> findByProductId(Integer productId);
	Set<Bid> findByProduct(Product product);
}
