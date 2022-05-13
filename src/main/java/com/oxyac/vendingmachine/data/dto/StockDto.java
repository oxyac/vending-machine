package com.oxyac.vendingmachine.data.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.oxyac.vendingmachine.data.entity.StockConfig;
import com.oxyac.vendingmachine.util.StringToLongConverter;
import lombok.Data;

import javax.persistence.Convert;
import java.util.List;

@Data
public class StockDto {

    @JsonProperty
    private StockConfig config;

    @JsonProperty
//    @Convert(converter= StringToLongConverter.class,attributeName="price")
    private List<ItemDto> items;

}
