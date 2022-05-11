package com.oxyac.vendingmachine.service;

import com.oxyac.vendingmachine.data.entity.Stock;
import com.oxyac.vendingmachine.rest.repr.StockForm;
import org.springframework.stereotype.Service;

@Service
public class StockService {

    private StockForm stock;

    public StockForm getStock() {
        return stock;
    }

    public void setStock(StockForm stock) {
        this.stock = stock;
    }
}
