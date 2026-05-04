package com.crudoftasks.createTasks.controller;

import com.crudoftasks.createTasks.dto.ErrorDTO;
import com.crudoftasks.createTasks.exception.NotFoundException;
import com.crudoftasks.createTasks.exception.UnauthorizedUserException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionHandlerController {
    @ExceptionHandler(exception = NotFoundException.class)
    public ResponseEntity<ErrorDTO> handleNotFoundException(NotFoundException ex){
        return ResponseEntity.status(404).body(new ErrorDTO(ex.getMessage()));
    }

    @ExceptionHandler(exception = UnauthorizedUserException.class)
    public ResponseEntity<ErrorDTO> handleUnauthorizedUserException(UnauthorizedUserException ex){
        return ResponseEntity.status(401).body(new ErrorDTO(ex.getMessage()));
    }

    @ExceptionHandler(exception = MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDTO> handleArgumentNotValidException(MethodArgumentNotValidException ex){
        return ResponseEntity.status(400).body(new ErrorDTO("Request mal formada, favor validar requisição feita. ErroMessage(): "+ex.getMessage()));
    }
}
