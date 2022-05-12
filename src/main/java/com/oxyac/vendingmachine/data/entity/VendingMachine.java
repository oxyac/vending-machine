package com.oxyac.vendingmachine.data.entity;

import com.oxyac.vendingmachine.rest.repr.StockForm;
import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Data
@Component
public class VendingMachine {

    private StockForm stock;

    private Long depositedAmount;

    private Boolean transactionInProgress;

    public VendingMachine() {
        this.transactionInProgress = false;
    }
}
