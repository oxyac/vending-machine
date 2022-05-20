package com.oxyac.vendingmachine.data.exception;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.oxyac.vendingmachine.data.exception.response.ExceptionResponse;
import com.oxyac.vendingmachine.data.exception.response.GenericExceptionResponse;
import com.oxyac.vendingmachine.data.exception.response.ValidationError;
import com.oxyac.vendingmachine.data.exception.response.ValidationExceptionResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.util.StringUtils;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.xml.bind.ValidationException;
import java.io.EOFException;
import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public GenericExceptionResponse handleError(Exception e) {
        return handle(e, ExceptionResponse.ErrorType.INTERNAL);
    }

    private GenericExceptionResponse handle(Throwable e, ExceptionResponse.ErrorType type) {
        return handle(e, "Server encountered an error", type);
    }

    private GenericExceptionResponse handle(Throwable e, String message, ExceptionResponse.ErrorType type) {
        GenericExceptionResponse response = new GenericExceptionResponse();
        response.setErrorType(type);
        response.setMessage(message);
        log.error(e.getMessage(), e);
        return response;
    }

    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public GenericExceptionResponse handleError(NullPointerException e) {
        return handle(e, ExceptionResponse.ErrorType.INTERNAL);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public GenericExceptionResponse handleError(NoHandlerFoundException e) {
        return handle(e, "Resource not found", ExceptionResponse.ErrorType.NOT_FOUND);
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ResponseBody
    public GenericExceptionResponse handleError(AccessDeniedException e) {
        return handle(e, "Access denied", ExceptionResponse.ErrorType.AUTHORIZATION);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public GenericExceptionResponse handleError(HttpMessageNotReadableException e) {
        GenericExceptionResponse response = new GenericExceptionResponse();
        log.error("Handling error", e);

        if (e.getCause() instanceof JsonMappingException) {
            response.setErrorType(ExceptionResponse.ErrorType.INVALID_JSON);
            response.setMessage("Failed to map JSON");
        } else if (e.getCause() instanceof JsonParseException) {
            response.setErrorType(ExceptionResponse.ErrorType.INVALID_JSON);
            response.setMessage("Failed to parse JSON");
        } else {
            response.setErrorType(ExceptionResponse.ErrorType.INVALID_JSON);
            response.setMessage("Empty request body");
        }
        return response;
    }

    @ExceptionHandler(JsonParseException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public GenericExceptionResponse handleError(JsonParseException e) {
        GenericExceptionResponse response = new GenericExceptionResponse();
        JsonMappingException jex = (JsonMappingException) e.getCause();
        response.setErrorType(ExceptionResponse.ErrorType.INVALID_JSON);
        response.setMessage(jex.getOriginalMessage());
        return response;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ValidationExceptionResponse handleError(MethodArgumentNotValidException e) {
        ValidationExceptionResponse response = new ValidationExceptionResponse();
        response.setErrors(buildErrorMap(e.getBindingResult().getFieldErrors()));
        return response;
    }

    @ExceptionHandler(EOFException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public GenericExceptionResponse handleError(EOFException e) {
        GenericExceptionResponse response = new GenericExceptionResponse();
        response.setMessage("Failed to parse request payload");
        return handle(e, "Failed to parse request payload", ExceptionResponse.ErrorType.INVALID_REQUEST);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ResponseBody
    public GenericExceptionResponse handleError(HttpRequestMethodNotSupportedException e) {
        GenericExceptionResponse response = new GenericExceptionResponse();
        response.setMessage("Method " + e.getMethod() + " not allowed for this endpoint");
        return handle(e, response.getMessage(), ExceptionResponse.ErrorType.INVALID_REQUEST);
    }

    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public GenericExceptionResponse handleValidationException(ValidationException e) {
        GenericExceptionResponse response = new GenericExceptionResponse();
        response.setErrorType(ExceptionResponse.ErrorType.INVALID_REQUEST);
        response.setMessage(e.getMessage());
        return response;
    }

    private Map<String, List<ValidationError>> buildErrorMap(List<FieldError> fieldErrors) {
        Map<String, List<ValidationError>> errorMap = new HashMap<>();
        for (FieldError fieldError : fieldErrors) {
            List<ValidationError> errorList = new ArrayList<>();
            ValidationError error = new ValidationError();
            error.setCode(StringUtils.uncapitalize(fieldError.getCode()));
            error.setField(fieldError.getField());
            error.setMessage(fieldError.getDefaultMessage());
            errorList.add(error);
            errorMap.put(fieldError.getField(), errorList);
        }
        return errorMap;
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public GenericExceptionResponse handleError(MissingServletRequestParameterException e) {
        return handle(
                e, "Missing required url parameter: " + e.getParameterName(),
                ExceptionResponse.ErrorType.INVALID_REQUEST
        );
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public GenericExceptionResponse handleError(MethodArgumentTypeMismatchException e) {
        return handle(e, "Failed to parse parameter: " + e.getName(), ExceptionResponse.ErrorType.INVALID_REQUEST);
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public GenericExceptionResponse handleError(HttpMediaTypeNotSupportedException e) {
        return handleWarning(
                e, "Invalid content type: " + e.getContentType().getType(),
                ExceptionResponse.ErrorType.INVALID_REQUEST
        );
    }


    private GenericExceptionResponse handleWarning(Throwable e, String message, ExceptionResponse.ErrorType type) {
        GenericExceptionResponse response = new GenericExceptionResponse();
        response.setErrorType(type);
        response.setMessage(message);
        log.error(e.getMessage(), e);
        return response;
    }
}
