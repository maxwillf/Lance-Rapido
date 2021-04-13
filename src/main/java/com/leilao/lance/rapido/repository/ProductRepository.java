package com.leilao.lance.rapido.repository;

import com.leilao.lance.rapido.model.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ProductRepository<T extends Product> extends JpaRepository<T,Integer> {
	T findByIdAndActiveTrue(Integer Id);
	T findByCreationTimeAndUserId(Date creationTime, Integer userId);
    List<T> findByUserId(Integer userId);
    List<T> findByBidsUserIdAndActiveTrue(Integer userId);
    List<T> findByUserIdAndActiveTrue(Integer userId);
    List<T> findByUserIdAndActiveFalseAndBidsNotNull(Integer userId);
    List<T> findByActiveTrue();
}