package com.kk.advice;

import com.kk.exception.GeoResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionHandlerClass {

    @ExceptionHandler(GeoResourceNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleGeoResourceNotFoundException(GeoResourceNotFoundException ex) {
        Map<String, String> errorMap = new HashMap<>();
            errorMap.put("errorMessage", ex.getMessage());
        return new ResponseEntity<>(errorMap,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map<String, String>> handleConstraintViolationException(ConstraintViolationException ex) {
        Map<String, String> errorMap = new HashMap<>();
        ex.getConstraintViolations().forEach(error -> {
            errorMap.put("errorMessage", error.getMessage());

        });
        return new ResponseEntity<>(errorMap,HttpStatus.BAD_REQUEST);
    }

}
