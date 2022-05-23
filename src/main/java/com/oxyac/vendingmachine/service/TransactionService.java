package com.oxyac.vendingmachine.service;

import com.oxyac.vendingmachine.data.dto.MachineResponseDto;
import com.oxyac.vendingmachine.data.entity.Item;
import com.oxyac.vendingmachine.data.entity.Session;
import com.oxyac.vendingmachine.data.entity.VendingMachine;
import com.oxyac.vendingmachine.data.repository.SessionRepository;
import com.oxyac.vendingmachine.data.repository.VendingMachineRepository;
import com.oxyac.vendingmachine.util.LongToBigDecimalConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@Service
public class TransactionService {
    private final SessionRepository sessionRepository;
    private final VendingMachineRepository vendingMachineRepository;
    public LongToBigDecimalConverter longToBigDecimalConverter;

    public TransactionService(LongToBigDecimalConverter longToBigDecimalConverter,
                              SessionRepository sessionRepository,
                              VendingMachineRepository vendingMachineRepository) {

        this.longToBigDecimalConverter = longToBigDecimalConverter;
        this.sessionRepository = sessionRepository;
        this.vendingMachineRepository = vendingMachineRepository;
    }

    public MachineResponseDto confirmPurchase(VendingMachine vendingMachine, Item item) {
        item.setAmount(item.getAmount() - 1);
        Long balance = vendingMachine.getDepositedAmount();
        Long change = balance - item.getPrice();
        vendingMachine.setDepositedAmount(0L);
        this.vendingMachineRepository.save(vendingMachine);

        Optional<Session> session = this.sessionRepository.findByVendingMachineId(vendingMachine.getId());
        if (session.isPresent()) {
            Session sessionEntity = session.get();
            sessionEntity.setDatetimeStopped(LocalDateTime.now());
            sessionEntity.setItem(item);
            this.sessionRepository.save(sessionEntity);
        } else {
            log.error("session is missing for vm:" + vendingMachine.toString());
        }

        return new MachineResponseDto(item, change);
    }
}
