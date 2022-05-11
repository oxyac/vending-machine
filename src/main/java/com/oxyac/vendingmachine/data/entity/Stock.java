package com.oxyac.vendingmachine.data.entity;

import lombok.Data;

import java.util.List;
@Data
public class Stock {
    private StockConfig config;

    private List<Item> items;
}
