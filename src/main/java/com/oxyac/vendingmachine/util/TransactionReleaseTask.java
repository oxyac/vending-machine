package com.oxyac.vendingmachine.util;

import com.oxyac.vendingmachine.data.entity.VendingMachine;
import com.oxyac.vendingmachine.service.ITransactionService;
import com.oxyac.vendingmachine.service.TransactionService;
import com.oxyac.vendingmachine.service.VendingMachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.TimerTask;
import java.util.UUID;

@Component
public class TransactionReleaseTask extends TimerTask {

    @Autowired
    public ITransactionService transactionService;

    private VendingMachine vendingMachine;


    @Override
    public void run() {

        this.transactionService.releaseTransaction(this.vendingMachine);

        System.out.println("Locked Vending Machine at: "
                + LocalDateTime.ofInstant(Instant.ofEpochMilli(scheduledExecutionTime()),
                ZoneId.systemDefault()));
    }

    public VendingMachine getVendingMachine() {
        return vendingMachine;
    }

    public void setVendingMachine(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }
}
