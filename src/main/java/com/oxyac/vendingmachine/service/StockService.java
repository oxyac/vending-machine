package com.oxyac.vendingmachine.service;

import com.oxyac.vendingmachine.data.entity.Stock;
import org.springframework.stereotype.Service;

@Service
public class StockService {

    private Stock stock;

    public Stock getStock() {
        return this.stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }
}
