package com.oxyac.vendingmachine.util;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

class StringToLongConverterTest {

    @Autowired
    private final StringToLongConverter stringToLongConverter;

    StringToLongConverterTest(StringToLongConverter stringToLongConverter) {
        this.stringToLongConverter = stringToLongConverter;
    }

    @Test
    public void whenConvertStringToLongUsingConverter_thenSuccess() {
        assertThat(
                stringToLongConverter.convert("$2.25")).isEqualTo(225);
    }
}