package com.leilao.lance.rapido.strategy.SaveProductStrategy;

import com.leilao.lance.rapido.model.Eletronico;
import com.leilao.lance.rapido.model.Movel;
import com.leilao.lance.rapido.model.Product;

public class EletronicoSaveProductStrategy extends SaveProductStrategyTemplateBase implements SaveProductStrategy {
    public EletronicoSaveProductStrategy(){};
    public Product productTypeSpecificChecking(Product product){
        if(!(product instanceof Eletronico)){
            return null;
        }
        Eletronico eletronico = (Eletronico) product;
        if (eletronico.getVoltagem() != 110 && eletronico.getVoltagem() != 220){
            return null;
        }
        else {
           return product;
        }
    }
}
