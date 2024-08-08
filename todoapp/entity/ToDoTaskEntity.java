package com.todoapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "taskentity_mst")
public class ToDoTaskEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String todotask;

    @Column(name = "email_todomst",nullable = false)
    private String email_todomst;

    private String taskDateTime;

    @Column(name = "finishFlag_tmst",nullable = false)
    private String finishFlag_tmst;


    private String finishDate;


    public ToDoTaskEntity(String todotask, String taskDateTime, String email_todomst, String finishFlag_tmst) {

        this.todotask = todotask;
        this.taskDateTime = taskDateTime;
        this.email_todomst = email_todomst;
        this.finishFlag_tmst = finishFlag_tmst;
    }



}
