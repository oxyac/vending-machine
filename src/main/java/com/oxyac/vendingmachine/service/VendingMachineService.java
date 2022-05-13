package com.oxyac.vendingmachine.service;

import com.oxyac.vendingmachine.data.dto.ItemDto;
import com.oxyac.vendingmachine.data.dto.MachineResponseDto;
import com.oxyac.vendingmachine.data.dto.StockDto;
import com.oxyac.vendingmachine.data.entity.Item;
import com.oxyac.vendingmachine.data.entity.VendingMachine;
import com.oxyac.vendingmachine.data.exception.InventoryNullException;
import com.oxyac.vendingmachine.data.entity.Stock;
import com.oxyac.vendingmachine.data.exception.LowBalanceException;
import com.oxyac.vendingmachine.data.exception.StockEmptyException;
import com.oxyac.vendingmachine.util.LongToBigDecimalConverter;
import com.oxyac.vendingmachine.util.StringToLongConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

@Slf4j
@Service
public class VendingMachineService implements IVendingMachineService, ITransactionService{


    @Autowired
    private VendingMachine vendingMachine;

    @Autowired
    private StringToLongConverter stringToLongConverter;

    @Autowired
    public LongToBigDecimalConverter longToBigDecimalConverter;

    @Override
    public Stock loadStock(StockDto stockDto) {


        Stock stock = new Stock();

        stock.setConfig(stockDto.getConfig());

        ListIterator<ItemDto> iterator = stockDto.getItems().listIterator();

        List<Item> items = new ArrayList<>();

        outerLoop:
        for (int i = 1; i <= stockDto.getConfig().getColumns(); i++) {

            char c = 'A';
            for (int j = 0; j < Integer.parseInt(stockDto.getConfig().getRows()); j++) {

                if(iterator.hasNext()){

                    ItemDto tempItem = iterator.next();


                    Item item = new Item(
                            tempItem.getName(),
                            tempItem.getAmount(),
                            stringToLongConverter.convert(tempItem.getPrice()));

                    item.setCol(i);
                    item.setRow(String.valueOf(c));

                    items.add(item);

                    c++;
                }
                else{
                    break outerLoop;
                }

            }
        }

        stock.setItems(items);

        this.vendingMachine.setStock(stock);

        return stock;
    }

    @Override
    public Stock getStock() throws InventoryNullException {
        Stock stock = this.vendingMachine.getStock();

        if (stock == null) {
            log.error("no stock");
            throw new InventoryNullException("-X POST /loadStock");
        }

        return stock;

    }

    @Override
    public Item findByRowCol(String row, Integer col) throws Exception {

        Stock stock = this.vendingMachine.getStock();

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

    @Override
    public MachineResponseDto processTransaction(Item item) throws Exception {

        if(item == null){
            log.info("not found");
            throw new EntityNotFoundException("-X GET /getStock");
        }

        if(item.getAmount() == 0){
            throw new StockEmptyException("Rupture de stock.");
        }

        if(this.vendingMachine.getDepositedAmount() < item.getPrice()) {
            Long amountLeft = item.getPrice() - this.vendingMachine.getDepositedAmount();
            throw new LowBalanceException("Insufficient funds. Introduce " +
                    longToBigDecimalConverter.convert(amountLeft) + "$");
        }

        return ITransactionService.confirmPurchase(this.vendingMachine, item);

    }


    public VendingMachine getVendingMachine() {
        return vendingMachine;
    }

    public void setVendingMachine(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

}
