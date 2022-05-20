package com.oxyac.vendingmachine.data.exception.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public interface Response {
    @JsonProperty("message")
    String getMessage();

    void setMessage(String message);
}
