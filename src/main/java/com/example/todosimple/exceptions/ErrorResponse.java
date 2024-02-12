/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.todosimple.exceptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 *
 * @author root
 */

@Getter
@Setter
@RequiredArgsConstructor
public class ErrorResponse {
    
    private final int status;
    
    private final String message;
    
    private String stackTrace;
    
    private List<ValidationError> errors;
    
    @Getter
    @Setter
    @RequiredArgsConstructor
    private static class ValidationError{
        private final String field;
        private final String message;
    }
    
    public void addValidationError(String field, String message){
        if(Objects.isNull(errors)){
            this.errors = new ArrayList<>(); 
        }
        this.errors.add(new ValidationError(field, message));
    }
}