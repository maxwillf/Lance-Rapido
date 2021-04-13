package com.leilao.lance.rapido.strategy;

import com.leilao.lance.rapido.model.Bid;
import com.leilao.lance.rapido.model.Product;
import com.leilao.lance.rapido.model.Veiculo;
import com.leilao.lance.rapido.repository.ProductRepository;

import java.util.HashSet;
import java.util.Set;

public class VeiculoAddBidStrategy implements ProductAddBidStrategy {
    public VeiculoAddBidStrategy(){};
    public Product execute(Product product, Bid bid){

        double bidDifference = 500.00;
        if(!(product instanceof Veiculo)){
            return null;
        }
        Veiculo veiculo = (Veiculo) product;

        //if (veiculo.getDocumento() == null){
        //    return null;
        //}

        Set<Bid> bids = product.getBids();
        if(bids == null || bids.isEmpty()){
            Set<Bid> newBids = new HashSet<Bid>() {
                {
                    add(bid);
                }
            };
            product.setBids(newBids);
        }
        else {
            Bid highestBid = bids.iterator().next();

            if (bid.getBidValue() - highestBid.getBidValue() < bidDifference){
                bids.add(bid);
                product.setBids(bids);
                return product;
            } else {
                return null;
            }
        }
        return null;
    }
}
