package com.leilao.lance.rapido.strategy.SaveProductStrategy;

import com.leilao.lance.rapido.model.Bid;
import com.leilao.lance.rapido.model.Product;

import java.util.Set;

abstract public class SaveProductStrategyTemplateBase implements SaveProductStrategy  {
   public Product checkProduct(Product product){
      if(product.getInitialBid() == null || product.getUser() == null ||
         product.getImages() == null || product.getImages().isEmpty()){
         return null;
      } else {
         return product;
      }
   }
   abstract Product productTypeSpecificChecking(Product product);
   final public Product execute(Product product){
      Product basicCheckedProduct = checkProduct(product);
      if(basicCheckedProduct == null) return null;
      return productTypeSpecificChecking(basicCheckedProduct);
   }
}
