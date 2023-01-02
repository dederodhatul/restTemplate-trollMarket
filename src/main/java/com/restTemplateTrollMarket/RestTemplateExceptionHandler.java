package com.restTemplateTrollMarket;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpStatusCodeException;

@RestControllerAdvice
public class RestTemplateExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(RestTemplateResponseException e) {

        ErrorResponse response = new ErrorResponse();
        response.setMessage(e.getMessage());
        response.setStatus(HttpStatus.NOT_FOUND.value());
        response.setTimeStamp(System.currentTimeMillis());
        response.setKeterangan("eror dari ObjectNotFound");

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler
    public ResponseEntity<String> handleException(HttpStatusCodeException e) {

        return new ResponseEntity<>(e.getResponseBodyAsString(), e.getStatusCode());
    }
}
