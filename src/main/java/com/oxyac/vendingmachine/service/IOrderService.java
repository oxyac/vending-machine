package com.oxyac.vendingmachine.service;

import com.oxyac.vendingmachine.data.entity.Order;

public interface IOrderService {
    Iterable<Order> getAllOrders();

    Order create(Order order);

    void update(Order order);
}