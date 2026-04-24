package com.crudoftasks.createTasks.controller;

import com.crudoftasks.createTasks.dto.ErrorDTO;
import com.crudoftasks.createTasks.exception.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController {
    @ExceptionHandler(exception = NotFoundException.class)
    public ResponseEntity<ErrorDTO> handleNotFoundException(NotFoundException ex){
        return ResponseEntity.status(404).body(new ErrorDTO(ex.getMessage()));
    }
}
