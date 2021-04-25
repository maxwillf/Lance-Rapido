package com.leilao.lance.rapido.strategy.AddBidStrategy;

import com.leilao.lance.rapido.model.Bid;
import com.leilao.lance.rapido.model.Product;

import java.util.HashSet;
import java.util.Set;

abstract public class ProductAddBidStrategyBase implements ProductAddBidStrategy {
   Product addBid(Product product,Bid bid, Double bidDifference){
      Set<Bid> bids = product.getBids();

      if(bids == null || bids.isEmpty()){
         Set<Bid> newBids = new HashSet<Bid>() {
            {
               add(bid);
            }
         };
         product.setBids(newBids);
         product.setHighestBid(bid);
         return product;
      }
      Bid highestBid = bids.iterator().next();
      if (bid.getBidValue() - highestBid.getBidValue() >= bidDifference){
         bids.add(bid);
         product.setBids(bids);
         product.setHighestBid(bid);
         return product;
      } else {
         return null;
      }
   }
   abstract public Product execute(Product product,Bid bid);
}
