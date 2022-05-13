package com.oxyac.vendingmachine.data.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.oxyac.vendingmachine.data.dto.ItemDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
public class Item {

    @JsonProperty
    private String name;

    @JsonProperty
    private Integer amount;

    @JsonProperty
    private Long price;

    @JsonProperty
    private String row;

    @JsonProperty
    private Integer col;

    public Item(String name, Integer amount, Long price) {
        this.name = name;
        this.amount = amount;
        this.price = price;
    }


}
