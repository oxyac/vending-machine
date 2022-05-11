package com.oxyac.vendingmachine.util;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


@Component
public class StringToLongConverter implements Converter<String, Long> {
    @Override
    public Long convert(String source) {
        String value = source.replaceAll("[^0-9]","");
        return Long.parseLong(value);
    }
}
