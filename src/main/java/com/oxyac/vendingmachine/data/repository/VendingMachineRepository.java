package com.oxyac.vendingmachine.data.repository;

import com.oxyac.vendingmachine.data.entity.Stock;
import com.oxyac.vendingmachine.data.entity.VendingMachine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VendingMachineRepository extends JpaRepository<VendingMachine, UUID> {
}