package com.leilao.lance.rapido.strategy.AddBidStrategy;

import com.leilao.lance.rapido.model.Bid;
import com.leilao.lance.rapido.model.Product;
import com.leilao.lance.rapido.model.Veiculo;

import java.util.HashSet;
import java.util.Set;

public class VeiculoAddBidStrategy extends ProductAddBidStrategyBase implements ProductAddBidStrategy {
    public VeiculoAddBidStrategy(){};
    public Product execute(Product product, Bid bid){

        double bidDifference = 500.00;
        if(!(product instanceof Veiculo)){
            return null;
        }
        return addBid(product,bid,bidDifference);
    }
}
