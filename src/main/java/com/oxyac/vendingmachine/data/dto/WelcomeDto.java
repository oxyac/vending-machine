package com.oxyac.vendingmachine.data.dto;

import com.oxyac.vendingmachine.data.entity.Stock;

import java.util.HashMap;

public class WelcomeDto {

    private String welcomeMessage;
    private HashMap<String, String> availableRoutes;
    private Stock stock;


    public HashMap<String, String> getAvailableRoutes() {
        return availableRoutes;
    }

    public void setAvailableRoutes(HashMap<String, String> availableRoutes) {
        this.availableRoutes = availableRoutes;
    }

    public String getWelcomeMessage() {
        return welcomeMessage;
    }

    public void setWelcomeMessage(String welcomeMessage) {
        this.welcomeMessage = welcomeMessage;
    }

    public WelcomeDto(String welcomeMessage, HashMap<String, String> availableRoutes, Stock stock) {
        this.welcomeMessage = welcomeMessage;
        this.availableRoutes = availableRoutes;
        this.stock = stock;
    }
}
