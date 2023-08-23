package com.kk.exception;

public class GeoResourceNotFoundException extends RuntimeException{

    public GeoResourceNotFoundException(String message) {
        super(message);
    }
}
