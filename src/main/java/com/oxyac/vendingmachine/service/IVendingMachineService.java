package com.oxyac.vendingmachine.service;

import com.oxyac.vendingmachine.data.dto.MachineResponseDto;
import com.oxyac.vendingmachine.data.dto.StockDto;
import com.oxyac.vendingmachine.data.entity.Item;
import com.oxyac.vendingmachine.data.entity.VendingMachine;
import com.oxyac.vendingmachine.data.exception.InventoryNullException;
import com.oxyac.vendingmachine.data.entity.Stock;

public interface IVendingMachineService {

    VendingMachine getVendingMachine();

    Stock getStock() throws InventoryNullException;

    Stock loadStock(StockDto stockDto);

    Item findByRowCol(String row, Integer col) throws Exception;

    Long depositCoin(Long deposited);

    MachineResponseDto processTransaction(Item item) throws Exception;
}
