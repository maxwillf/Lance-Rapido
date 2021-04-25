package com.leilao.lance.rapido.strategy.SaveProductStrategy;

import com.leilao.lance.rapido.model.Product;
import com.leilao.lance.rapido.model.Veiculo;

public class VeiculoSaveProductStrategy extends SaveProductStrategyTemplateBase implements SaveProductStrategy {
    public VeiculoSaveProductStrategy(){};
    public Product productTypeSpecificChecking(Product product){
        if(!(product instanceof Veiculo)){
            return null;
        }
        Veiculo veiculo = (Veiculo) product;
        if (veiculo.getDocumento() == null){
            return null;
        }
        return product;
    }
}
