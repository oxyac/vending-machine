package com.oxyac.vendingmachine.data.dto;

import com.oxyac.vendingmachine.data.entity.Product;
import lombok.Data;

import java.io.Serializable;

@Data
public class OrderProductDto implements Serializable {


    private Product product;
    private Integer quantity;


    public Integer getQuantity() {
        return quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}