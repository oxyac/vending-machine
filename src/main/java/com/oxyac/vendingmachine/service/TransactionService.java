package com.oxyac.vendingmachine.service;

import com.oxyac.vendingmachine.data.dto.MachineResponseDto;
import com.oxyac.vendingmachine.data.entity.Item;
import com.oxyac.vendingmachine.data.entity.Session;
import com.oxyac.vendingmachine.data.entity.VendingMachine;
import com.oxyac.vendingmachine.data.repository.*;
import com.oxyac.vendingmachine.util.LongToBigDecimalConverter;
import com.oxyac.vendingmachine.util.StringToLongConverter;
import com.oxyac.vendingmachine.util.TransactionReleaseTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;

@Slf4j
@Service
public class TransactionService implements ITransactionService{
    private final VendingMachineRepository vendingMachineRepository;

    private final StringToLongConverter stringToLongConverter;

    public LongToBigDecimalConverter longToBigDecimalConverter;

    private final ItemRepository itemRepository;

    private final SessionRepository sessionRepository;

    private final StockRepository stockRepository;

    private StockConfigRepository stockConfigRepository;

    public TransactionService(VendingMachineRepository vendingMachineRepository,
                                 StringToLongConverter stringToLongConverter,
                                 LongToBigDecimalConverter longToBigDecimalConverter,
                                 ItemRepository itemRepository,
                                 SessionRepository sessionRepository,
                                 StockRepository stockRepository,
                                 StockConfigRepository stockConfigRepository) {

        this.vendingMachineRepository = vendingMachineRepository;
        this.stringToLongConverter = stringToLongConverter;
        this.longToBigDecimalConverter = longToBigDecimalConverter;
        this.itemRepository = itemRepository;
        this.sessionRepository = sessionRepository;
        this.stockRepository = stockRepository;
        this.stockConfigRepository = stockConfigRepository;

    }

    @Override
    public void lockTransaction(VendingMachine vendingMachine) {
        vendingMachine.setTransactionInProgress(true);

        TransactionReleaseTask task = new TransactionReleaseTask();
        task.setVendingMachine(vendingMachine);

        new Timer().schedule(task, 0, 60000 * 10);

    }

    @Override
    public void releaseTransaction(VendingMachine vendingMachine) {

        vendingMachine.setTransactionInProgress(false);

        VendingMachine vendingMachine1 = this.vendingMachineRepository.save(vendingMachine);
        log.info("Transaction ended for VM___:" + vendingMachine1.toString());
    }

    @Override
    public MachineResponseDto confirmPurchase(VendingMachine vendingMachine, Item item) {

        item.setAmount(item.getAmount() - 1 );

        Long balance = vendingMachine.getDepositedAmount();

        Long change = balance - item.getPrice();

        vendingMachine.setDepositedAmount(null);

        Optional<Session> session = this.sessionRepository.findByVendingMachineId(vendingMachine.getId());

        if (session.isPresent()){
            Session sessionEntity = session.get();
            sessionEntity.setDatetimeStopped(LocalDateTime.now());
            sessionEntity.setItem(item);
            this.sessionRepository.save(sessionEntity);
        }
        else{
            log.error("session is missing for vm:" + vendingMachine.toString());
        }

        releaseTransaction(vendingMachine);

        return new MachineResponseDto(item, change);
    }
}
