/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.todosimple.services;

import com.example.todosimple.models.Task;
import com.example.todosimple.models.User;
import com.example.todosimple.repositories.TaskRepository;
import com.example.todosimple.services.exceptions.DataBindingViolationException;
import com.example.todosimple.services.exceptions.ObjectNotFoundException;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author root
 */
@Service
public class TaskService {
    
    
    @Autowired
    private TaskRepository taskRepository;
    
    @Autowired
    private UserService userService;
    
    
    @Transactional
    public Task findById(Long id){
        Optional<Task> task = this.taskRepository.findById(id);
        return task.orElseThrow(() -> new ObjectNotFoundException(
                "Tarefa não encontrada! id: " + id + ", Tipo: " + Task.class.getName()
        ));
    }
    
    public List<Task> findAllByUserId(Long userId){
        List<Task> tasks = this.taskRepository.findByUser_Id(userId);
        return tasks;
    }
    
    @Transactional
    public Task create(Task obj){
        User user = this.userService.findbyID(obj.getUser().getId());
        obj.setId(null);
        obj.setUser(user);
        return this.taskRepository.save(obj);
    }
    
    @Transactional
    public Task update(Task obj){
        Task newObj = findById(obj.getId());
        newObj.setDescription(obj.getDescription());
        return this.taskRepository.save(newObj);
    }
    
    public void delete(Long id){
        findById(id);
        try{
            this.taskRepository.deleteById(id);
        }catch(Exception e){
            throw new DataBindingViolationException("Não é possível deletar, pois há entidades relacionadas");
        }
    }
    
    
}
