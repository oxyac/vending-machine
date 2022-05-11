package com.oxyac.vendingmachine.data.entity;

import lombok.Data;

@Data
public class Item {
    private String name;

    private Integer amount;

    private Long price;

    private Integer row;

    private Integer col;
}
