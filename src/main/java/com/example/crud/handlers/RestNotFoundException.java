package com.example.crud.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.crud.model.error.ErrorMessage;
import com.example.crud.model.exception.ResourceNotFoundException;

@ControllerAdvice
public class RestNotFoundException {
    

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleRestNotFoundException(ResourceNotFoundException exception) {
        
        ErrorMessage Mensagem = new ErrorMessage("Not_Found", HttpStatus.NOT_FOUND.value(), exception.getMessage());

        return new ResponseEntity<>(Mensagem, HttpStatus.NOT_FOUND);
    }
}
