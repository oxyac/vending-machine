package com.oxyac.vendingmachine.data.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.oxyac.vendingmachine.data.entity.Stock;

import java.util.HashMap;
import java.util.UUID;

public class WelcomeDto {

    @JsonProperty
    private String welcomeMessage;

    @JsonProperty
    private HashMap<String, String> availableRoutes;

    @JsonProperty
    private Stock stock;

    @JsonProperty
    private UUID serialNumber;


    public WelcomeDto(String welcomeMessage, HashMap<String, String> availableRoutes, Stock stock, UUID serialNumber) {
        this.welcomeMessage = welcomeMessage;
        this.availableRoutes = availableRoutes;
        this.stock = stock;
        this.serialNumber = serialNumber;
    }
}
