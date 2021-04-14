package com.leilao.lance.rapido.strategy.SaveProductStrategy;

import com.leilao.lance.rapido.model.Bid;
import com.leilao.lance.rapido.model.Product;

import java.util.Set;

public class SaveProductStrategyBase implements SaveProductStrategy  {
   public Product checkProduct(Product product){
      if(product.getInitialBid() == null || product.getUser() == null ||
         product.getImages() == null || product.getImages().isEmpty()){
         return null;
      } else {
         return product;
      }
   }
   public Product execute(Product product){
      return checkProduct(product);
   }
}
