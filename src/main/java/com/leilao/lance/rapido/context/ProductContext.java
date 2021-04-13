package com.leilao.lance.rapido.context;

import com.leilao.lance.rapido.model.Bid;
import com.leilao.lance.rapido.model.Product;
import com.leilao.lance.rapido.strategy.ProductAddBidStrategy;
import com.leilao.lance.rapido.strategy.VeiculoAddBidStrategy;
import lombok.Getter;
import lombok.Setter;

public class ProductContext {
    @Getter
    @Setter
    private ProductAddBidStrategy productStrategy;

    public ProductContext(){
        this.productStrategy = new VeiculoAddBidStrategy();
    }

    public Product addBid(Product product, Bid bid){
        return productStrategy.execute(product,bid);
    }
}
