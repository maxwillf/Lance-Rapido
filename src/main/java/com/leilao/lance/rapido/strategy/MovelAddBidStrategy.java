package com.leilao.lance.rapido.strategy;

import com.leilao.lance.rapido.model.Bid;
import com.leilao.lance.rapido.model.Movel;
import com.leilao.lance.rapido.model.Product;

import java.util.HashSet;
import java.util.Set;

public class MovelAddBidStrategy implements ProductAddBidStrategy {
    public MovelAddBidStrategy(){};
    public Product execute(Product product, Bid bid){

        double bidDifference = 20.00;
        if(!(product instanceof Movel)){
            return null;
        }
        Movel movel = (Movel) product;

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
