package com.oxyac.vendingmachine.rest.controller;


import com.oxyac.vendingmachine.data.dto.MachineResponseDto;
import com.oxyac.vendingmachine.data.dto.WelcomeDto;
import com.oxyac.vendingmachine.data.entity.Item;
import com.oxyac.vendingmachine.data.entity.VendingMachine;
import com.oxyac.vendingmachine.data.exception.InventoryNullException;
import com.oxyac.vendingmachine.data.exception.LowBalanceException;
import com.oxyac.vendingmachine.data.exception.StockEmptyException;
import com.oxyac.vendingmachine.rest.repr.StockForm;
import com.oxyac.vendingmachine.service.ITransactionService;
import com.oxyac.vendingmachine.service.IVendingMachineService;
import com.oxyac.vendingmachine.util.StringToLongConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.*;

@RestController
@Slf4j
public class VendingMachineController {

    private final IVendingMachineService stockService;
    private final StringToLongConverter stringToLongConverter;

    public VendingMachineController(IVendingMachineService stockService, StringToLongConverter stringToLongConverter) {
        this.stockService = stockService;
        this.stringToLongConverter = stringToLongConverter;
    }


    @RequestMapping("/loadStock")
    public ResponseEntity<WelcomeDto> reStock(@RequestBody StockForm stockForm) {

        stockService.loadStock(stockForm);

        HashMap<String, String> routes = new HashMap<>();

        routes.put("/getStock", "Return all item info");
        routes.put("/loadStock", "Load new products into vending machine");
        routes.put("/selection&row={B}&col={2}", "Returns item info");
        routes.put("/deposit&amount={225}", "Deposit coins into machine");
        routes.put("/purchase&row={B}&col={2}", "Make a purchase with deposited money");

        WelcomeDto welcome = new WelcomeDto(routes, "Here is a description of all available routes");
        return ResponseEntity.ok(welcome);
    }

    @RequestMapping("/getStock")
    public ResponseEntity<StockForm> getStock() throws InventoryNullException {

        return ResponseEntity.ok(stockService.getStock());

    }

    @RequestMapping(value = "/selection", method = RequestMethod.GET)
    public ResponseEntity<Item> selectProduct(@RequestParam String row, @RequestParam Integer col) throws Exception {

        log.info(row);
        log.info(String.valueOf(col));

        Item item = stockService.findByRowCol(row, col);

        if(item == null){
            log.info("not found");
            throw new EntityNotFoundException("-X GET /getStock");
        }

        log.info("item:" + item.toString());
        return new ResponseEntity<>(item, HttpStatus.OK);

    }


    @RequestMapping(value = "/purchase", method = RequestMethod.GET)
    public ResponseEntity<MachineResponseDto> purchaseProduct(@RequestParam String row,
                                                            @RequestParam Integer col) throws Exception {

        log.info(row);
        log.info(String.valueOf(col));


        Item item = stockService.findByRowCol(row, col);

        VendingMachine vendingMachine = stockService.getVendingMachine();

        Long balance = vendingMachine.getDepositedAmount();


        if(item == null){
            log.info("not found");
            throw new EntityNotFoundException("-X GET /getStock");
        }

        if(item.getAmount() == 0){
            throw new StockEmptyException("Rupture de stock.");
        }

        if(balance < item.getPrice()) {
            Long amountLeft = item.getPrice() - balance;
            throw new LowBalanceException("Insufficient funds. Introduce " +
                    stringToLongConverter.convertToString(amountLeft) + "$");
        }

        log.info(item.toString());
        MachineResponseDto responseDto = ITransactionService.confirmPurchase(vendingMachine, item, balance);

        return new ResponseEntity<>(responseDto, HttpStatus.OK);


    }

    @RequestMapping(value = "/deposit", method = RequestMethod.GET)
    public ResponseEntity<Long> depositCoin(@RequestParam Long amount) throws Exception {

        log.info(String.valueOf(amount));

        Long moneyDeposited = stockService.depositCoin(amount);

        return new ResponseEntity<>(moneyDeposited, HttpStatus.OK);

    }
}
