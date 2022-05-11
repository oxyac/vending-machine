package com.oxyac.vendingmachine.rest.repr;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


@Data
public class ConfigForm {
    @JsonProperty
    private Integer rows;
    @JsonProperty
    private Integer columns;
}
