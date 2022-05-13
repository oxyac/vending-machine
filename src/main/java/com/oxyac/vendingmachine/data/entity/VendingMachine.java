package com.oxyac.vendingmachine.data.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class VendingMachine {

    private Stock stock;

    private Long depositedAmount;

    private Boolean transactionInProgress;

    public VendingMachine() {
        this.transactionInProgress = false;
        this.depositedAmount = 0L;
    }
}
