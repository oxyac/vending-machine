package com.oxyac.vendingmachine.data.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;

import static org.springframework.http.HttpStatus.*;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
@Slf4j
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String error = "Malformed JSON request";
        return buildResponseEntity(new VendingMachineError(HttpStatus.BAD_REQUEST, error, ex));
    }

    private ResponseEntity<Object> buildResponseEntity(VendingMachineError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @ExceptionHandler(LowBalanceException.class)
    protected ResponseEntity<Object> handleLowBalance(
            LowBalanceException ex) {
        log.info("threw LowBalanceException");
        VendingMachineError apiError = new VendingMachineError(NOT_ACCEPTABLE) {};
        apiError.setMessage(ex.getMessage());
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(StockEmptyException.class)
    protected ResponseEntity<Object> handleStockEmpty(
            StockEmptyException ex) {
        log.info("threw StockEmptyException");
        VendingMachineError apiError = new VendingMachineError(CONFLICT) {};
        apiError.setMessage(ex.getMessage());
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotFound(
            EntityNotFoundException ex) {
        log.info("threw EntityNotFoundException");
        VendingMachineError apiError = new VendingMachineError(NOT_FOUND) {};
        apiError.setMessage(ex.getMessage());
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(InventoryNullException.class)
    protected ResponseEntity<Object> handleException(
            InventoryNullException ex) {
        log.info("threw unknown Exception");
        VendingMachineError apiError = new VendingMachineError(METHOD_NOT_ALLOWED) {};
        apiError.setMessage(ex.getMessage());
        return buildResponseEntity(apiError);
    }

}