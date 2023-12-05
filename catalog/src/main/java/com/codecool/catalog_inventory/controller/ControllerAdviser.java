package com.codecool.catalog_inventory.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

@ControllerAdvice
public class ControllerAdviser {

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<ErrorBody> handleHttpClientErrorException(HttpClientErrorException ex) {
        ErrorBody err = new ErrorBody(ex.getMessage());
        return ResponseEntity.status(ex.getStatusCode()).body(err);
    }

    public record ErrorBody(String message){}
}
