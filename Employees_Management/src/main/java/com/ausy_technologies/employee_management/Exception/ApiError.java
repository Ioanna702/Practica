package com.ausy_technologies.employee_management.Exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ApiError {
    private String message; //titlul erorii
    private String errorMessage; //mesajul erorii
    private HttpStatus httpStatus; //statusul erorii (4xx sau 5xx)

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime localDateTime;  //pentru a tine cont de cand a avut loc eroarea (de regula se numeste timestamp)

    private ApiError() {
        localDateTime = LocalDateTime.now();
    }

    ApiError(HttpStatus httpStatus, Throwable throwableException) {
        this();
        this.httpStatus = httpStatus;
        this.message = "Error!!!";
        this.errorMessage = throwableException.getLocalizedMessage();
    }

    ApiError(HttpStatus httpStatus, String message, Throwable throwableException) {
        this();
        this.httpStatus = httpStatus;
        this.message = message;
        this.errorMessage = throwableException.getLocalizedMessage();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }
}