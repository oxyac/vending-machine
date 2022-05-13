package com.oxyac.vendingmachine.util;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;


@Component
public class LongToBigDecimalConverter implements Converter<Long, BigDecimal> {


    public BigDecimal convertToString(Long amount){
        return new BigDecimal(amount).movePointLeft(2);
    }

    @Override
    public BigDecimal convert(Long source) {
        return new BigDecimal(source).movePointLeft(2);
    }
}
