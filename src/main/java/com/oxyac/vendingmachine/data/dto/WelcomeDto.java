package com.oxyac.vendingmachine.data.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.oxyac.vendingmachine.data.entity.Item;
import com.oxyac.vendingmachine.data.entity.Stock;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class WelcomeDto {

    @JsonProperty
    private String welcomeMessage;

    @JsonProperty
    private HashMap<String, String> availableRoutes;

    @JsonProperty
    private UUID serialNumber;

    @JsonProperty
    private List<Item> items;




    public WelcomeDto(String welcomeMessage, HashMap<String, String> availableRoutes, List<Item> stock, UUID serialNumber) {
        this.welcomeMessage = welcomeMessage;
        this.availableRoutes = availableRoutes;
        this.items = stock;
        this.serialNumber = serialNumber;
    }
}
