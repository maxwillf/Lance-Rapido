package com.leilao.lance.rapido.strategy.AddBidStrategy;

import com.leilao.lance.rapido.model.Bid;
import com.leilao.lance.rapido.model.Movel;
import com.leilao.lance.rapido.model.Product;

import java.util.HashSet;
import java.util.Set;

public class MovelAddBidStrategy extends ProductAddBidStrategyBase implements ProductAddBidStrategy {
    public MovelAddBidStrategy(){};
    public Product execute(Product product, Bid bid){

        double bidDifference = 20.00;
        if(!(product instanceof Movel)){
            return null;
        }
        return addBid(product,bid,bidDifference);
    }
}
