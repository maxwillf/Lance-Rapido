package com.leilao.lance.rapido.context;

import com.leilao.lance.rapido.model.Bid;
import com.leilao.lance.rapido.model.Product;
import com.leilao.lance.rapido.strategy.AddBidStrategy.ProductAddBidStrategy;
import com.leilao.lance.rapido.strategy.AddBidStrategy.VeiculoAddBidStrategy;
import lombok.Getter;
import lombok.Setter;

public class ProductAddBidContext {
    @Getter
    @Setter
    private ProductAddBidStrategy productStrategy;

    public ProductAddBidContext(){
        this.productStrategy = new VeiculoAddBidStrategy();
    }

    public Product addBid(Product product, Bid bid){
        return productStrategy.execute(product,bid);
    }
}
