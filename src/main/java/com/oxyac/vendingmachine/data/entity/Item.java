package com.oxyac.vendingmachine.data.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

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
}
