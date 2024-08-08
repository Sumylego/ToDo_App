package com.todoapp.repository;

import com.todoapp.entity.ToDoEntity;
import com.todoapp.entity.ToDoTaskEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ToDoTaskRepository extends JpaRepository<ToDoTaskEntity,Long> {

    @Query("select t from ToDoTaskEntity t where t.email_todomst = :emailvalue and t.finishFlag_tmst = 'N'")
    List<ToDoTaskEntity> findByEmailVal(@Param("emailvalue") String emailvalue);

    @Query("select t from ToDoTaskEntity t where t.email_todomst = :emailvalue and t.finishFlag_tmst = 'Y'")
    List<ToDoTaskEntity> findByEmailValflag(@Param("emailvalue") String emailvalue);

}
