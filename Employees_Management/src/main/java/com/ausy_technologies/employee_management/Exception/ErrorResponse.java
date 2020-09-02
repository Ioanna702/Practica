package com.ausy_technologies.employee_management.Exception;

public class ErrorResponse extends RuntimeException{
    public ErrorResponse(String message){
        super(message);
    }
}
