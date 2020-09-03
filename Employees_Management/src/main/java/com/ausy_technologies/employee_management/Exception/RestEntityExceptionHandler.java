package com.ausy_technologies.employee_management.Exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class RestEntityExceptionHandler extends ResponseEntityExceptionHandler {

    private ResponseEntity<Object>createResponseEntity(ApiError apiError){
        //cream un raspuns in caz de eroare bazat pe ApiError
        return new ResponseEntity<>(apiError, apiError.getHttpStatus());
    }

    @Override //in cazul in care nu e poate fi deserializata o anumita informatie din json in java
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException httpMessageNotReadableException, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return createResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, "Wrong JSON request.",httpMessageNotReadableException ));
    }

    @Override //pentru a customiza raspunsul la eroarea primita atunci cand alegem un verb gresit in Http(POST, GET, etc) pentru actiunea ce vrem sa o facem
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException httpRequestMethodNotSupportedException, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return createResponseEntity(new ApiError(HttpStatus.BAD_GATEWAY,"Wrong method used.",httpRequestMethodNotSupportedException));
    }

    @ExceptionHandler(Exception.class) //handler pentru un caz general de exceptie
    public ResponseEntity<Object> handleException (Exception e, HttpServletResponse httpServletResponse){
        ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, e);
        return createResponseEntity(apiError);
    }
}
