package com.leilao.lance.rapido.strategy;

import com.leilao.lance.rapido.model.Bid;
import com.leilao.lance.rapido.model.Product;
import com.leilao.lance.rapido.model.Eletronico;

import java.util.HashSet;
import java.util.Set;

public class EletronicoAddBidStrategy implements ProductAddBidStrategy {
    public EletronicoAddBidStrategy(){};
    public Product execute(Product product, Bid bid){

        double bidDifference = 10.00;
        if(!(product instanceof Eletronico)){
            return null;
        }
        Eletronico eletronico = (Eletronico) product;
        if (eletronico.getVoltagem() != 110 || eletronico.getVoltagem() != 220){
            return null;
        }

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
