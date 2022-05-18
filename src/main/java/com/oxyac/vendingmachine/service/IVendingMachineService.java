package com.oxyac.vendingmachine.service;

import com.oxyac.vendingmachine.data.dto.MachineResponseDto;
import com.oxyac.vendingmachine.data.dto.StockDto;
import com.oxyac.vendingmachine.data.dto.StockResponseDto;
import com.oxyac.vendingmachine.data.entity.Item;
import com.oxyac.vendingmachine.data.entity.VendingMachine;
import com.oxyac.vendingmachine.data.exception.InventoryNullException;
import com.oxyac.vendingmachine.data.entity.Stock;
import com.oxyac.vendingmachine.data.exception.WrongMachineException;

import java.util.Optional;
import java.util.UUID;

public interface IVendingMachineService {

    Stock loadStock(StockDto stockDto);

    StockResponseDto getStockById(UUID id) throws InventoryNullException, WrongMachineException;

    Item findByRowCol(UUID id, String row, Integer col) throws Exception;

    Long depositCoin(UUID id, Long deposited);

    MachineResponseDto processTransaction(UUID id, String row, Integer col) throws Exception;
}
