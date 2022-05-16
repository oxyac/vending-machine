package com.oxyac.vendingmachine.service;

import com.oxyac.vendingmachine.data.dto.MachineResponseDto;
import com.oxyac.vendingmachine.data.entity.Item;
import com.oxyac.vendingmachine.data.entity.VendingMachine;
import com.oxyac.vendingmachine.util.TransactionReleaseTask;

import java.util.Timer;

public interface ITransactionService {

    void lockTransaction(VendingMachine vendingMachine);

    void releaseTransaction(VendingMachine vendingMachine);

    MachineResponseDto confirmPurchase(VendingMachine vendingMachine, Item item);
}
