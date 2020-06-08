package io.blueharvest.atm.controller;

import io.blueharvest.atm.exception.EntityExistsException;
import io.blueharvest.atm.exception.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    public static final String TIMESTAMP = "timestamp";
    public static final String STATUS = "status";
    public static final String ERROR = "error";


    @ExceptionHandler(EntityNotFoundException.class)
    public final ResponseEntity<Object> handleNotFoundException(
            EntityNotFoundException ex, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put(TIMESTAMP, new Date());
        body.put(STATUS, HttpStatus.NOT_FOUND.value());
        body.put(ERROR, ex.getMessage());
        return new ResponseEntity(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({EntityExistsException.class})
    public final ResponseEntity<Object> handleExistException(Exception ex, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put(TIMESTAMP, new Date());
        body.put(STATUS, HttpStatus.CONFLICT.value());
        body.put(ERROR, ex.getMessage());
        return new ResponseEntity(body, HttpStatus.CONFLICT);
    }
}
