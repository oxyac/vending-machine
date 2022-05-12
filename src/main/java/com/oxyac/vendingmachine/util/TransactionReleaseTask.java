package com.oxyac.vendingmachine.util;

import com.oxyac.vendingmachine.service.ITransactionService;
import com.oxyac.vendingmachine.service.VendingMachineService;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.TimerTask;

public class TransactionReleaseTask extends TimerTask {


    public VendingMachineService vendingMachineService;


    @Override
    public void run() {

        ITransactionService.releaseTransaction(vendingMachineService.getVendingMachine());

        System.out.println("Locked Vending Machine at: "
                + LocalDateTime.ofInstant(Instant.ofEpochMilli(scheduledExecutionTime()),
                ZoneId.systemDefault()));
    }
}
