package com.todoapp.service;


import com.todoapp.dto.TaskDto;
import com.todoapp.dto.ToDoDto;
import com.todoapp.entity.ToDoEntity;
import com.todoapp.entity.ToDoTaskEntity;
import jakarta.servlet.http.HttpSession;

import java.util.List;

public interface TodoService {
    void createToDo(ToDoDto toDoDto);

    ToDoDto findByUsername(String email);

    void createTaskToDo(ToDoTaskEntity toDoTaskEntity);



    List<TaskDto> findByEmailVal(String strEmailValue);

    List<TaskDto> findByEmailValflag(String strEmailValue);

    TaskDto findbyId(Long id);

    void updateTofinish(TaskDto taskDto);




}
