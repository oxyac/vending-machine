package com.oxyac.vendingmachine.util;


import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class StringToLongConverter implements Converter<String, Long> {
    @Override
    public Long convert(String source) {

        String value = source.replaceAll("[^0-9]","");
        log.info(source + "__convertTo__" + value);
        return Long.parseLong(value);
    }
}
