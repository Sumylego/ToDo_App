package com.todoapp.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ToDoDto {

       private Long id;

       @NotEmpty(message = "name should not be empty")
       private String name;


       @Email
       @NotEmpty(message = "email should not be empty")
       private String email;



       @NotEmpty(message = "contact should not be empty")
       private  String contact;




       @NotEmpty(message = "password should not be empty")
       private String  password;

       private LocalDateTime timestamp;

       private  String cheVal;

       public ToDoDto(Long id, String contact, String email, String name, String password, LocalDateTime timestamp) {
              this.id = id;
              this.contact = contact;
              this.email = email;
              this.name = name;
              this.password = password;
              this.timestamp = timestamp;
       }

}
