/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.todosimple.controllers;

import com.example.todosimple.models.Task;
import com.example.todosimple.services.TaskService;
import com.example.todosimple.services.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.net.URI;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 *
 * @author root
 */

@RestController
@RequestMapping("/task")
@Validated 
@Api(value = "/api", description = "API REST Task")
public class TaskController {
    
    @Autowired
    private TaskService taskService; 
    
    @Autowired
    private UserService userService;
    
    @GetMapping("/{id}")
    @ApiOperation(value = "Return Object Task")
    public ResponseEntity<Task> findById(@PathVariable Long id){
        Task obj = this.taskService.findById(id);
        return ResponseEntity.ok(obj);
    }
    
    @GetMapping("/user/{userId}")
    @ApiOperation(value = "Return All Objects Task")
    public ResponseEntity<List<Task>> findAllByUserId(@PathVariable Long userId){
        this.userService.findbyID(userId);
        List<Task> objs = this.taskService.findAllByUserId(userId);
        return ResponseEntity.ok().body(objs);
        
    }
    
    @PostMapping
    @Validated
    @ApiOperation(value = "Create Task")
    public ResponseEntity<Void> create(@Valid @RequestBody Task obj){
        this.taskService.create(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
    
    @PutMapping("/{id}")
    @Validated
    @ApiOperation(value = "Update Task")
    public ResponseEntity<Void> update (@Valid @RequestBody Task obj, @PathVariable Long id){
        obj.setId(id);
        this.taskService.update(obj);
        return ResponseEntity.noContent().build();
    }
    
    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete Task")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        this.taskService.delete(id);
        return ResponseEntity.noContent().build();
    }
   
    
}
