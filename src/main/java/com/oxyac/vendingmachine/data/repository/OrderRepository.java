package com.oxyac.vendingmachine.data.repository;

import com.oxyac.vendingmachine.data.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}