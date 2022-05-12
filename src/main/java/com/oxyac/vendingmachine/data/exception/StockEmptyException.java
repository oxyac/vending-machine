package com.oxyac.vendingmachine.data.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class StockEmptyException extends Exception {
    public StockEmptyException() {
    }

    public StockEmptyException(String message) {
        super(message);
    }

    public StockEmptyException(String message, Throwable cause) {
        super(message, cause);
    }

    public StockEmptyException(Throwable cause) {
        super(cause);
    }

}
