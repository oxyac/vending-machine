package com.oxyac.vendingmachine.service;

import com.oxyac.vendingmachine.data.dto.MachineResponseDto;
import com.oxyac.vendingmachine.data.entity.Item;
import com.oxyac.vendingmachine.data.entity.VendingMachine;
import com.oxyac.vendingmachine.util.TransactionReleaseTask;

import java.util.Timer;

public interface ITransactionService {

    static void lockTransaction(VendingMachine vendingMachine) {

        vendingMachine.setTransactionInProgress(true);

        //eat all user coins after 10 minutes
        new Timer().schedule(new TransactionReleaseTask(), 0, 60000 * 10);

    }

    static void releaseTransaction(VendingMachine vendingMachine){

        vendingMachine.setTransactionInProgress(false);

    }

    static MachineResponseDto confirmPurchase(VendingMachine vendingMachine, Item item, Long balance){

        item.setAmount(item.getAmount() - 1 );

        Long change = balance - item.getPrice();

        releaseTransaction(vendingMachine);

        return new MachineResponseDto(item, change);
    }
}
