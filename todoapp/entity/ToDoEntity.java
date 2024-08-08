package com.todoapp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
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
@Table(name = "todo_mst")
public class ToDoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String name;

    @Column(name = "email",nullable = false,unique = true)
    private String email;

    private  String contact;

    private String  password;

    private LocalDateTime timestamp;

    @Transient
    private  String cheVal;


    public ToDoEntity(Long id, String contact, String email, String name, String password, LocalDateTime timestamp) {
        this.id = id;
        this.contact = contact;
        this.email = email;
        this.name = name;
        this.password = password;
        this.timestamp = timestamp;
    }



}
