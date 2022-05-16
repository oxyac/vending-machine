package com.oxyac.vendingmachine.data.repository;

import com.oxyac.vendingmachine.data.entity.StockConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockConfigRepository extends JpaRepository<StockConfig, Long> {
}