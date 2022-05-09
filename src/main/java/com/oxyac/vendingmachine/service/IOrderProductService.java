package com.oxyac.vendingmachine.service;

import com.oxyac.vendingmachine.data.entity.OrderProduct;
import com.sun.istack.NotNull;
import org.springframework.validation.annotation.Validated;


public interface IOrderProductService {

    OrderProduct create( OrderProduct orderProduct);
}
