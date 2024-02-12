/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.todosimple.repositories;

import com.example.todosimple.models.Task;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author root
 */

@Repository
public interface TaskRepository extends JpaRepository<Task, Long>{
    
    // @Query(value = "SELECT t FROM T ask t WHERE t.user.id = :user_id") // : referencia o parametro
    //List<Task> findByUser_Id(@Param("user_id") Long id);
    
    List<Task> findByUser_Id(Long id);

}
