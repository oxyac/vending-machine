package com.oxyac.vendingmachine.data.exception.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"errorType", "message", "errorRef"})
public class GenericExceptionResponse implements ExceptionResponse {
    private String message;
    private ErrorType type;

    @Override
    public String getErrorType() {
        return type != null ? type.name() : "Unknown";
    }

    @Override
    public void setErrorType(ErrorType type) {
        this.type = type;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public void setMessage(String message) {
        this.message = message;
    }
}
