package com.oxyac.vendingmachine.data.service;

import com.oxyac.vendingmachine.data.entity.Order;
import com.oxyac.vendingmachine.data.repository.OrderRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class OrderService implements IOrderService{

    private OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
    }

    @Override
    public Iterable<Order> getAllOrders() {
        return this.orderRepository.findAll();
    }

    @Override
    public Order create(Order order) {
        order.setDate(LocalDate.now());
        return this.orderRepository.save(order);
    }

    @Override
    public void update(Order order) {
        this.orderRepository.save(order);
    }
}
