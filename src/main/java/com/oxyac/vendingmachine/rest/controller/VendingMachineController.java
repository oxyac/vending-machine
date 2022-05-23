package com.oxyac.vendingmachine.rest.controller;


import com.oxyac.vendingmachine.data.dto.StockResponseDto;
import com.oxyac.vendingmachine.data.exception.InventoryNullException;
import com.oxyac.vendingmachine.data.exception.LowBalanceException;
import com.oxyac.vendingmachine.data.exception.StockEmptyException;
import com.oxyac.vendingmachine.service.VendingMachineService;
import com.oxyac.vendingmachine.util.LongToBigDecimalConverter;
import com.oxyac.vendingmachine.data.dto.MachineResponseDto;
import com.oxyac.vendingmachine.service.TransactionService;
import com.oxyac.vendingmachine.data.entity.VendingMachine;
import com.oxyac.vendingmachine.data.dto.WelcomeDto;
import com.oxyac.vendingmachine.data.entity.Stock;
import com.oxyac.vendingmachine.data.dto.StockDto;
import com.oxyac.vendingmachine.data.entity.Item;
import org.springframework.web.bind.annotation.*;
import javax.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

@RestController
@Slf4j
public class VendingMachineController {

    private final VendingMachineService vendingMachineService;
    public final LongToBigDecimalConverter longToBigDecimalConverter;

    public VendingMachineController(VendingMachineService vendingMachineService, LongToBigDecimalConverter longToBigDecimalConverter) {
        this.vendingMachineService = vendingMachineService;
        this.longToBigDecimalConverter = longToBigDecimalConverter;
    }


    @RequestMapping("/loadStock")
    public ResponseEntity<WelcomeDto> loadStock(@RequestBody StockDto stockDto) {
        Stock stock = vendingMachineService.loadStock(stockDto);
        HashMap<String, String> routes = new HashMap<>();

        routes.put("/getStock?machine_id={uuid}", "Return all item info");
        routes.put("/loadStock", "Load and parse JSON containing new products");
        routes.put("/selection&row={B}&col={2}&machine_id={uuid}", "Returns item info");
        routes.put("/deposit&amount={225}&machine_id={uuid}", "Deposit coins into machine");
        routes.put("/purchase&row={B}&col={2}&machine_id={uuid}", "Make a purchase with deposited money");

        WelcomeDto welcome = new WelcomeDto("Please provide the serial number in all future requests",
                routes, stock.getItems(), stock.getVendingMachine().getId());

        return ResponseEntity.ok(welcome);
    }

    @RequestMapping("/getStock")
    public ResponseEntity<StockResponseDto> getStockById(@RequestParam UUID machine_id) throws Exception {

        StockResponseDto stock =  vendingMachineService.getStockById(machine_id);

        return ResponseEntity.ok(stock);

    }

    @RequestMapping(value = "/selection", method = RequestMethod.GET)
    public ResponseEntity<Item> selectProduct(@RequestParam String row,
                                              @RequestParam Integer col,
                                              @RequestParam UUID machine_id) throws Exception {

        log.info(row);
        log.info(String.valueOf(col));

        Item item = vendingMachineService.findByRowCol(machine_id, row, col);

        if(item == null){
            log.info("not found");
            throw new EntityNotFoundException("-X GET /getStock");
        }

        log.info("item:" + item.toString());
        return new ResponseEntity<>(item, HttpStatus.OK);

    }


    @RequestMapping(value = "/purchase", method = RequestMethod.GET)
    public ResponseEntity<MachineResponseDto> purchaseProduct(@RequestParam String row,
                                                              @RequestParam Integer col,
                                                              @RequestParam UUID machine_id) throws Exception {
        log.info("ROW:__"+row+"COL:__"+ col);
        MachineResponseDto responseDto = vendingMachineService.processTransaction(machine_id, row, col);
        log.info("TRANSACTION_SUCCESS_FOR_ITEM:___" + responseDto.toString());

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @RequestMapping(value = "/deposit", method = RequestMethod.GET)
    public ResponseEntity<Long> depositCoin(@RequestParam Long amount, @RequestParam UUID machine_id) throws Exception {
        log.info(String.valueOf(amount));
        Long moneyDeposited = vendingMachineService.depositCoin(machine_id, amount);

        return new ResponseEntity<>(moneyDeposited, HttpStatus.OK);
    }
}
