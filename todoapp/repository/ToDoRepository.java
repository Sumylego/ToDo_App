package com.todoapp.repository;

import com.todoapp.entity.ToDoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ToDoRepository extends JpaRepository<ToDoEntity,Long> {

    @Query("select s from ToDoEntity s where s.email = :email")
    ToDoEntity findByUsername(@Param("email") String email);
}
