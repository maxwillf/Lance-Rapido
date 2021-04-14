package com.leilao.lance.rapido.strategy.SaveProductStrategy;

import com.leilao.lance.rapido.model.Bid;
import com.leilao.lance.rapido.model.Product;
import com.leilao.lance.rapido.model.Veiculo;

import java.util.HashSet;
import java.util.Set;

public class VeiculoSaveProductStrategy extends SaveProductStrategyBase implements SaveProductStrategy {
    public VeiculoSaveProductStrategy(){};
    public Product execute(Product product){
        if(!(product instanceof Veiculo)){
            return null;
        }
        Product basicCheckedProduct = checkProduct(product);
        if(basicCheckedProduct == null) return null;
        Veiculo veiculo = (Veiculo) product;

        if (veiculo.getDocumento() == null){
            return null;
        }
        return product;
    }
}
