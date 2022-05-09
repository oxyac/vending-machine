package com.oxyac.vendingmachine.service;

import com.oxyac.vendingmachine.data.entity.OrderProduct;
import com.oxyac.vendingmachine.data.repository.OrderProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderProductService implements IOrderProductService{

    @Autowired
    private OrderProductRepository orderProductRepository;


    @Override
    public OrderProduct create(OrderProduct orderProduct) {
        return this.orderProductRepository.save(orderProduct);
    }


}
