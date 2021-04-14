package com.leilao.lance.rapido.strategy.AddBidStrategy;

import com.leilao.lance.rapido.model.Bid;
import com.leilao.lance.rapido.model.Product;
import com.leilao.lance.rapido.model.Eletronico;

import java.util.HashSet;
import java.util.Set;

public class EletronicoAddBidStrategy extends ProductAddBidStrategyBase implements ProductAddBidStrategy {
    public EletronicoAddBidStrategy(){};
    public Product execute(Product product, Bid bid){

        double bidDifference = 10.00;
        if(!(product instanceof Eletronico)){
            return null;
        }
        return addBid(product,bid,bidDifference);
    }
}
