package com.leilao.lance.rapido.strategy.AddBidStrategy;

import com.leilao.lance.rapido.model.Bid;
import com.leilao.lance.rapido.model.Product;
import com.leilao.lance.rapido.repository.ProductRepository;


public interface ProductAddBidStrategy {
    public Product execute(Product product, Bid bid);
}
