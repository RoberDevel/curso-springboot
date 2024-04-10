package com.example.springbooterror.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.example.springbooterror.exceptions.UserNotFoundException;
import com.example.springbooterror.model.domain.Error;

@RestControllerAdvice
public class HandlerExceptionController {

    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<?> divisionByZero(Exception e) {
        Error error = new Error();
        error.setDate(LocalDate.now());
        error.setError("Division por cero");
        error.setMessage(e.getMessage());
        error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        // return ResponseEntity.internalServerError().body(error);
    }

    @ExceptionHandler(NumberFormatException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, String> numberFormatException(NumberFormatException e) {
        Map<String, String> error = new HashMap<>();
        error.put("message", e.getMessage());
        error.put("error", "Formato de número incorrecto");
        error.put("status", HttpStatus.INTERNAL_SERVER_ERROR.toString());
        error.put("date", LocalDate.now().toString());
        return error;
    }

    @ExceptionHandler({ NullPointerException.class, HttpMessageNotWritableException.class,
            UserNotFoundException.class })
    public Map<String, String> userNotFoundException(Exception e) {
        Map<String, String> error = new HashMap<>();
        error.put("message", e.getMessage());
        error.put("error", "El usuario o role no existe");
        error.put("status", HttpStatus.INTERNAL_SERVER_ERROR.toString());
        error.put("date", LocalDate.now().toString());
        return error;
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<Error> notFoundExc(NoHandlerFoundException e) {
        Error error = new Error();
        error.setDate(LocalDate.now());
        error.setError("Api rest no encontrada");
        error.setMessage(e.getMessage());
        error.setStatus(HttpStatus.NOT_FOUND.value());

        // return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(error);
    }

}
