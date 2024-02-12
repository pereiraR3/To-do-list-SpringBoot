/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.todosimple.services.exceptions;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author root
 */

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class DataBindingViolationException  extends DataIntegrityViolationException {
    
    public DataBindingViolationException(String message){
        super(message);
    }
    
}
