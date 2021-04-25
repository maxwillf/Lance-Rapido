package com.leilao.lance.rapido.strategy.SaveProductStrategy;

import com.leilao.lance.rapido.model.Movel;
import com.leilao.lance.rapido.model.Product;

public class MovelSaveProductStrategy extends SaveProductStrategyTemplateBase implements SaveProductStrategy {
    public MovelSaveProductStrategy(){};
    public Product productTypeSpecificChecking(Product product){

        if(!(product instanceof Movel)){
            return null;
        }
        return product;
    }
}

