package com.oxyac.vendingmachine.rest.repr;

import com.oxyac.vendingmachine.data.dto.StockResponseDto;
import com.oxyac.vendingmachine.data.entity.Item;
import com.oxyac.vendingmachine.data.entity.Stock;
import com.oxyac.vendingmachine.data.entity.VendingMachine;
import com.oxyac.vendingmachine.data.exception.WrongMachineException;
import com.oxyac.vendingmachine.data.repository.VendingMachineRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Component
public class StockMapper {

    @Autowired
    private VendingMachineRepository vendingMachineRepository;

    public StockResponseDto toDto(Stock stock) throws WrongMachineException {

        Optional<VendingMachine> vendingMachine = vendingMachineRepository.findById(stock.getVendingMachine().getId());

        if(vendingMachine.isEmpty()){
            log.error("wrong machine id");
            throw new WrongMachineException("-X POST /loadStock");
        }

        UUID machine_id = vendingMachine.get().getId();

        List<Item> items = stock.getItems();

        Long deposited = vendingMachine.get().getDepositedAmount();

        return new StockResponseDto(machine_id, items, deposited);
    }

//    public User toUser(UserCreationDTO userDTO) {
//        return new User(userDTO.getName(), userDTO.getPassword(), new ArrayList<>());
//    }
}