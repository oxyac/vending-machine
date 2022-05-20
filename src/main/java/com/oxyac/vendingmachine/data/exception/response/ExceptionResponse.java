package com.oxyac.vendingmachine.data.exception.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public interface ExceptionResponse extends Response {
    enum ErrorType {
        INTERNAL,
        CONFIGURATION,
        AUTHENTICATION,
        AUTHORIZATION,
        VALIDATION,
        NOT_FOUND,
        INVALID_JSON,
        INVALID_REQUEST
    }

    @JsonProperty("errorType")
    String getErrorType();

    void setErrorType(ErrorType type);
}
