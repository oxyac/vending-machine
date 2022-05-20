package com.oxyac.vendingmachine.data.exception.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ValidationError {
    @JsonProperty
    private String field;
    @JsonProperty
    private String code;
    @JsonProperty
    private String message;

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
