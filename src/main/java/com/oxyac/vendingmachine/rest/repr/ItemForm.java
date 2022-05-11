package com.oxyac.vendingmachine.rest.repr;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ItemForm {
    @JsonProperty
    private String name;
    @JsonProperty
    private Integer amount;
    @JsonProperty
    private Long price;
}
