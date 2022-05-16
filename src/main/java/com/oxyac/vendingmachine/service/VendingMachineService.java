package com.oxyac.vendingmachine.service;

import com.oxyac.vendingmachine.data.dto.ItemDto;
import com.oxyac.vendingmachine.data.dto.MachineResponseDto;
import com.oxyac.vendingmachine.data.dto.StockDto;
import com.oxyac.vendingmachine.data.entity.*;
import com.oxyac.vendingmachine.data.exception.InventoryNullException;
import com.oxyac.vendingmachine.data.exception.LowBalanceException;
import com.oxyac.vendingmachine.data.exception.StockEmptyException;
import com.oxyac.vendingmachine.data.repository.*;
import com.oxyac.vendingmachine.util.LongToBigDecimalConverter;
import com.oxyac.vendingmachine.util.StringToLongConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.*;

@Slf4j
@Service
public class VendingMachineService implements IVendingMachineService {


    private final VendingMachineRepository vendingMachineRepository;

    private final StringToLongConverter stringToLongConverter;

    public LongToBigDecimalConverter longToBigDecimalConverter;

    private final ItemRepository itemRepository;

    private final SessionRepository sessionRepository;

    private final StockRepository stockRepository;

    private StockConfigRepository stockConfigRepository;

    private ITransactionService transactionService;

    public VendingMachineService(VendingMachineRepository vendingMachineRepository,
                                 StringToLongConverter stringToLongConverter,
                                 LongToBigDecimalConverter longToBigDecimalConverter,
                                 ItemRepository itemRepository,
                                 SessionRepository sessionRepository,
                                 StockRepository stockRepository,
                                 StockConfigRepository stockConfigRepository,
                                 ITransactionService transactionService) {

        this.vendingMachineRepository = vendingMachineRepository;
        this.stringToLongConverter = stringToLongConverter;
        this.longToBigDecimalConverter = longToBigDecimalConverter;
        this.itemRepository = itemRepository;
        this.sessionRepository = sessionRepository;
        this.stockRepository = stockRepository;
        this.stockConfigRepository = stockConfigRepository;
        this.transactionService = transactionService;

    }

    @Override
    public Stock loadStock(StockDto stockDto) {


        Stock stock = new Stock(stockDto.getConfig());

        this.stockConfigRepository.save(stockDto.getConfig());
        Session session =  this.sessionRepository.save(new Session());
        VendingMachine vendingMachine = this.vendingMachineRepository.save(new VendingMachine());
        stock.setVendingMachine(vendingMachine);

        stock.setConfig(stockDto.getConfig());

        ListIterator<ItemDto> iterator = stockDto.getItems().listIterator();

        List<Item> items = new ArrayList<>();

        outerLoop:
        for (int i = 1; i <= stockDto.getConfig().getColumns(); i++) {

            char c = 'A';
            for (int j = 0; j < Integer.parseInt(stockDto.getConfig().getRows()); j++) {

                if (iterator.hasNext()) {

                    ItemDto tempItem = iterator.next();


                    Item item = new Item(
                            tempItem.getName(),
                            tempItem.getAmount(),
                            stringToLongConverter.convert(tempItem.getPrice()));

                    item.setCol(i);
                    item.setRow(String.valueOf(c));

                    items.add(item);
                    log.info(item.toString());
                    c++;
                } else {
                    break outerLoop;
                }

            }
        }


        this.itemRepository.saveAll(items);

        stock.setItems(items);
        stock = this.stockRepository.save(stock);

        return stock;
    }

    @Override
    public Optional<Stock> getStockById(UUID id) throws InventoryNullException {

        Optional<Stock> stock = this.stockRepository.findByVendingMachineId(id);

        if (stock == null) {
            log.error("no stock");
            throw new InventoryNullException("-X POST /loadStock");
        }

        return stock;

    }

    @Override
    public Item findByRowCol(UUID id, String row, Integer col) throws Exception {


        Optional<Stock> stock = this.stockRepository.findByVendingMachineId(id);

        log.info(stock.toString());
        if (stock.isEmpty()) {
            log.info("no stock");
            throw new InventoryNullException("-X POST /loadStock");
        }

        Stock foundStock = stock.get();

        for (Item stockItem : foundStock.getItems()) {
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
    public Long depositCoin(UUID id, Long deposited) {

        VendingMachine vendingMachine = this.vendingMachineRepository.getById(id);

        Long balance = vendingMachine.getDepositedAmount();

        if(balance == null){
            this.transactionService.lockTransaction(vendingMachine);

        } else {

            vendingMachine.setDepositedAmount(vendingMachine.getDepositedAmount() + deposited);
        }

        VendingMachine vendingMachineEntity = vendingMachineRepository.save(vendingMachine);

        return vendingMachineEntity.getDepositedAmount();
    }

    @Override
    public MachineResponseDto processTransaction(UUID machine_id, String row, Integer col) throws Exception {


        VendingMachine vendingMachine = this.vendingMachineRepository.getById(machine_id);

        Item desiredItem = this.findByRowCol(machine_id, row, col);

        if (desiredItem == null) {
            log.info("not found product ____ : " + row + ":" + col);
            throw new EntityNotFoundException("-X GET /getStock");
        }

        if (desiredItem.getAmount() == 0) {
            throw new StockEmptyException("Rupture de stock.");
        }

        if (vendingMachine.getDepositedAmount() < desiredItem.getPrice()) {
            Long amountLeft = desiredItem.getPrice() - vendingMachine.getDepositedAmount();
            throw new LowBalanceException("Insufficient funds. Introduce " +
                    longToBigDecimalConverter.convert(amountLeft) + "$");
        }

        return this.transactionService.confirmPurchase(vendingMachine, desiredItem);

    }


}
