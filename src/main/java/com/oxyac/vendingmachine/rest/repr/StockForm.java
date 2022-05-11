package com.oxyac.vendingmachine.rest.repr;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.oxyac.vendingmachine.data.entity.Item;
import com.oxyac.vendingmachine.data.entity.StockConfig;
import lombok.Data;

import java.util.List;

@Data
public class StockForm {
    @JsonProperty
    private StockConfig config;
    @JsonProperty
    private List<Item> items;
}
