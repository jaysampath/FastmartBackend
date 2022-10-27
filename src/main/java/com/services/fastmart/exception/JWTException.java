package com.services.fastmart.exception;

public class JWTException extends RuntimeException{

    public JWTException(String message){
        super(message);
    }
}