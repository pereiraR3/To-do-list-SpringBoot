/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.todosimple.services;

import com.example.todosimple.models.User;
import com.example.todosimple.repositories.UserRepository;
import com.example.todosimple.services.exceptions.DataBindingViolationException;
import com.example.todosimple.services.exceptions.ObjectNotFoundException;
import jakarta.transaction.Transactional;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author root
 */

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    
    public User findbyID(Long id){
        Optional<User> user = this.userRepository.findById(id);// usamos optional para caso n tiver dado receber o dado null e ficar tudo bem
        return user.orElseThrow(() -> new ObjectNotFoundException(
        "Usuário não encontrado! Id: " + id + ", Tipos: " + User.class.getName())
        );
    }

    @Transactional
    public User create(User obj){
        obj.setId(null);
        obj = this.userRepository.save(obj);
        return obj;
    }
    
    @Transactional
    public User update(User obj){
        User newObj = findbyID(obj.getId());
        newObj.setPassword(obj.getPassword());
        return this.userRepository.save(newObj);
    }
    
    @Transactional
    public void delete(Long id){
        findbyID(id);
        try{
           this.userRepository.deleteById(id);
        }catch(Exception e){
            throw new DataBindingViolationException("Não é possível excuir pois há entidades relacionadas");
        }
    }
}
