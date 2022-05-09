package com.oxyac.vendingmachine.data.repository;

import com.oxyac.vendingmachine.data.entity.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderProductRepository extends JpaRepository<OrderProduct, Long> {
}
