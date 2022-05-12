package com.oxyac.vendingmachine.data.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class VendingMachineError {

    private HttpStatus status;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;
    private String message;
    private String debugMessage;
    private List<SubError> subErrors;

    private VendingMachineError() {
        timestamp = LocalDateTime.now();
    }

    VendingMachineError(HttpStatus status) {
        this();
        this.status = status;
    }

    VendingMachineError(HttpStatus status, Throwable ex) {
        this();
        this.status = status;
        this.message = "Unexpected error";
        this.debugMessage = ex.getLocalizedMessage();
    }

    VendingMachineError(HttpStatus status, String message, Throwable ex) {
        this();
        this.status = status;
        this.message = message;
        this.debugMessage = ex.getLocalizedMessage();
    }
}
