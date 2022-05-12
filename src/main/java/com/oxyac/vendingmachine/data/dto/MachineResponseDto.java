package com.oxyac.vendingmachine.data.dto;

import com.oxyac.vendingmachine.data.entity.Item;
import lombok.Data;

@Data
public class MachineResponseDto {

    private Item item;

    private Long change;

    private String message;

    public MachineResponseDto(Item item, Long change) {
        this.item = item;
        this.change = change;
        this.message = "Enjoy your ".concat(this.getItem().getName());
    }
}
