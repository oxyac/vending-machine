package com.oxyac.vendingmachine.data.dto;

import com.oxyac.vendingmachine.data.entity.Item;
import lombok.Data;

@Data
public class TransactionInProgressDto {

    private Item desiredItem;

    private Long moneyRemaining;

    private Long moneyIn;

    public TransactionInProgressDto(Item desiredItem, Long moneyRemaining, Long moneyIn) {
        this.desiredItem = desiredItem;
        this.moneyRemaining = moneyRemaining;
        this.moneyIn = moneyIn;
    }
}
