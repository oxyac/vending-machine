package com.oxyac.vendingmachine.service;

import com.oxyac.vendingmachine.data.dto.TransactionInProgressDto;
import com.oxyac.vendingmachine.data.entity.Item;
import com.oxyac.vendingmachine.data.entity.VendingMachine;
import com.oxyac.vendingmachine.data.exception.InventoryNullException;
import com.oxyac.vendingmachine.data.exception.TransactionInProgressException;
import com.oxyac.vendingmachine.rest.repr.StockForm;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Slf4j
@Service
public class VendingMachineService implements IVendingMachineService, ITransactionService{


    @Autowired
    private VendingMachine vendingMachine;

    @Override
    public void loadStock(StockForm stockForm) {
        this.vendingMachine.setStock(stockForm);
    }

    @Override
    public StockForm getStock() throws InventoryNullException {
        StockForm stock = this.vendingMachine.getStock();

        if (stock == null) {
            log.error("no stock");
            throw new InventoryNullException("-X POST /loadStock");
        }

        return stock;

    }

    @Override
    public Long returnChange() {
        return null;
    }

    @Override
    public Item findByRowCol(String row, Integer col) throws Exception {

        StockForm stock = this.vendingMachine.getStock();

        log.info(stock.toString());
        if (stock == null) {
            log.info("no stock");
            throw new InventoryNullException("-X POST /loadStock");
        }

        for (Item stockItem : stock.getItems()) {
            log.info(String.valueOf(stockItem.getRow().equals(row)));
            log.info(String.valueOf(stockItem.getCol().equals(col)));
            if (stockItem.getRow().equals(row) &&
                    stockItem.getCol().equals(col)) {

                log.info("item:" + stockItem.toString());
                return stockItem;
            }
        }

        return null;

    }

    @Override
    public Long depositCoin(Long deposited) {


        if(vendingMachine.getDepositedAmount() == null){

            ITransactionService.lockTransaction(this.vendingMachine);

            vendingMachine.setDepositedAmount(deposited);

        }
        else{

            vendingMachine.setDepositedAmount(vendingMachine.getDepositedAmount() + deposited);
        }

        return vendingMachine.getDepositedAmount();
    }


    public VendingMachine getVendingMachine() {
        return vendingMachine;
    }

    public void setVendingMachine(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

}
