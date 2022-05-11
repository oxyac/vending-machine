package com.oxyac.vendingmachine.rest.controller;


import com.oxyac.vendingmachine.data.entity.Item;
import com.oxyac.vendingmachine.data.entity.Stock;
import com.oxyac.vendingmachine.data.entity.StockConfig;
import com.oxyac.vendingmachine.rest.repr.StockForm;
import com.oxyac.vendingmachine.service.StockService;
import com.oxyac.vendingmachine.util.StringToLongConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@RestController
@Slf4j
public class VendingMachineController {

    private final StockService stockService;
    private final StringToLongConverter stringToLongConverter;

    public VendingMachineController(StockService stockService, StringToLongConverter stringToLongConverter) {
        this.stockService = stockService;
        this.stringToLongConverter = stringToLongConverter;
    }


    @RequestMapping("/loadStock")
    public ResponseEntity<StockForm> reStock(@RequestBody StockForm stockForm) {

        stockService.setStock(stockForm);

        return ResponseEntity.ok(stockForm);
    }

    @RequestMapping(name = "/selection", method = RequestMethod.GET)
    public void selectProduct(@RequestParam("row") Integer row, @RequestParam("col") Integer col) {

        StockForm stock = stockService.getStock();

        Item stockItem = (Item) stock.getItems().stream().filter(
                item-> item.getCol().equals(col) && item.getRow().equals(row));

            System.out.println(stockItem);

    }

}
