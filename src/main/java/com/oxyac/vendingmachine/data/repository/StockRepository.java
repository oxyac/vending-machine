package com.oxyac.vendingmachine.data.repository;

import com.oxyac.vendingmachine.data.entity.Stock;
import com.oxyac.vendingmachine.data.entity.VendingMachine;
import com.oxyac.vendingmachine.data.exception.VendingMachineError;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface StockRepository extends JpaRepository<Stock, UUID> {
    Optional<Stock> findByVendingMachineId(UUID id);

    Optional<Stock> findByVendingMachine(VendingMachine vendingMachine);
}