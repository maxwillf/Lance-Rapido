package com.leilao.lance.rapido.context;

import com.leilao.lance.rapido.model.Bid;
import com.leilao.lance.rapido.model.Product;
import com.leilao.lance.rapido.strategy.AddBidStrategy.ProductAddBidStrategy;
import com.leilao.lance.rapido.strategy.AddBidStrategy.VeiculoAddBidStrategy;
import com.leilao.lance.rapido.strategy.SaveProductStrategy.SaveProductStrategy;
import com.leilao.lance.rapido.strategy.SaveProductStrategy.SaveProductStrategyBase;
import lombok.Getter;
import lombok.Setter;

public class SaveProductContext {
    @Getter
    @Setter
    private SaveProductStrategy saveProductStrategy;

    public SaveProductContext(){
        this.saveProductStrategy = new SaveProductStrategyBase();
    }

    public Product checkCanSaveProduct(Product product){
        return saveProductStrategy.execute(product);
    }
}
