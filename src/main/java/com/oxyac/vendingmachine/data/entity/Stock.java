package com.oxyac.vendingmachine.data.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Stock {
    @JsonProperty
    private StockConfig config;
    @JsonProperty
    private List<Item> items;
}
