package com.oxyac.vendingmachine.data.dto;

import java.util.HashMap;

public class WelcomeDto {

    private String welcomeMessage;
    private HashMap<String, String> availableRoutes;


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

    public WelcomeDto(HashMap<String, String> availableRoutes, String welcomeMessage) {
        this.welcomeMessage = welcomeMessage;
        this.availableRoutes = availableRoutes;
    }
}
