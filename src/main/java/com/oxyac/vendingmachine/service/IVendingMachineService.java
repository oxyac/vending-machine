package com.oxyac.vendingmachine.service;

import com.oxyac.vendingmachine.data.dto.MachineResponseDto;
import com.oxyac.vendingmachine.data.entity.Item;
import com.oxyac.vendingmachine.data.entity.VendingMachine;
import com.oxyac.vendingmachine.data.exception.InventoryNullException;
import com.oxyac.vendingmachine.rest.repr.StockForm;

public interface IVendingMachineService {

    VendingMachine getVendingMachine();

    StockForm getStock() throws InventoryNullException;

    void loadStock(StockForm stockForm);

    Long returnChange();

    Item findByRowCol(String row, Integer col) throws Exception;

    Long depositCoin(Long deposited);

}
