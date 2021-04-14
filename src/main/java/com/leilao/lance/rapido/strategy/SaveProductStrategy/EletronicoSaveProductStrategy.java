package com.leilao.lance.rapido.strategy.SaveProductStrategy;

import com.leilao.lance.rapido.model.Bid;
import com.leilao.lance.rapido.model.Eletronico;
import com.leilao.lance.rapido.model.Product;

import java.util.HashSet;
import java.util.Set;

public class EletronicoSaveProductStrategy extends SaveProductStrategyBase implements SaveProductStrategy {
    public EletronicoSaveProductStrategy(){};
    public Product execute(Product product){
        Product basicCheckedProduct = checkProduct(product);
        if(basicCheckedProduct == null) return null;
        Eletronico eletronico = (Eletronico) basicCheckedProduct;
        if (eletronico.getVoltagem() != 110 && eletronico.getVoltagem() != 220){
            return null;
        }
        else {
           return product;
        }
    }
}
