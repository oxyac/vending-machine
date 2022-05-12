package com.oxyac.vendingmachine.service;

import com.oxyac.vendingmachine.rest.repr.StockForm;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StockService {

    private StockForm stock;

    public StockForm getStock() {
        return this.stock;
    }

    public void setStock(StockForm stock) {
        this.stock = stock;
    }
}
