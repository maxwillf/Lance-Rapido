package com.leilao.lance.rapido.strategy.SaveProductStrategy;

import com.leilao.lance.rapido.model.Bid;
import com.leilao.lance.rapido.model.Movel;
import com.leilao.lance.rapido.model.Product;

import java.util.HashSet;
import java.util.Set;

public class MovelSaveProductStrategy extends SaveProductStrategyBase implements SaveProductStrategy {
    public MovelSaveProductStrategy(){};
    public Product execute(Product product){

        if(!(product instanceof Movel)){
            return null;
        }
        Movel movel = (Movel) product;
        Product basicCheckedProduct = checkProduct(product);
        if(basicCheckedProduct == null) return null;
        return product;
    }
}

