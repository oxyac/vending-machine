package com.oxyac.vendingmachine.service;

import com.oxyac.vendingmachine.data.entity.Order;
import com.oxyac.vendingmachine.data.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class OrderService implements IOrderService{

    @Autowired
    private OrderRepository orderRepository;

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
