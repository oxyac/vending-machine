package com.oxyac.vendingmachine.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.convert.ApplicationConversionService;
import org.springframework.core.convert.ConversionService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class VendingMachineControllerTest {

    @Autowired
    private final ConversionService conversionService;

    VendingMachineControllerTest(ConversionService conversionService){
        this.conversionService = conversionService;
    }

    @Test
    public void whenConvertStringToIntegerUsingDefaultConverter_thenSuccess() {
        assertThat(
                conversionService.convert("25", Integer.class)).isEqualTo(25);
    }
}