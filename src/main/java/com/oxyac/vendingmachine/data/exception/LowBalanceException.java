package com.oxyac.vendingmachine.data.exception;

public class LowBalanceException extends Exception {
    public LowBalanceException() {
    }

    public LowBalanceException(String message) {
        super(message);
    }

    public LowBalanceException(String message, Throwable cause) {
        super(message, cause);
    }
}
