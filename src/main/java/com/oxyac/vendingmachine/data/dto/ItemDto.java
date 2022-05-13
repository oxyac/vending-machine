package com.oxyac.vendingmachine.data.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ItemDto {

    @JsonProperty
    private String name;

    @JsonProperty
    private Integer amount;

    @JsonProperty
    private String price;

}
