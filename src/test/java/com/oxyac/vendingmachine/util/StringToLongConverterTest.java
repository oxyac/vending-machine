package com.oxyac.vendingmachine.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

class StringToLongConverterTest {

    private StringToLongConverter stringToLongConverter;

    @BeforeEach
    public void initializeTest(){
        this.stringToLongConverter = new StringToLongConverter();
    }

    @Test
    public void whenConvertStringToLongUsingConverter_thenSuccess() {
        assertThat(
                stringToLongConverter.convert("$2.25")).isEqualTo(225L);
    }
}