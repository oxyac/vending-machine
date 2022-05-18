package com.oxyac.vendingmachine.data.dto;

import com.oxyac.vendingmachine.data.entity.Item;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class StockResponseDto {

    private UUID machine_id;

    List<Item> items;

    private Long deposited;

    public StockResponseDto(UUID machine_id, List<Item> items, Long deposited) {
        this.machine_id = machine_id;
        this.items = items;
        this.deposited = deposited;
    }
}
