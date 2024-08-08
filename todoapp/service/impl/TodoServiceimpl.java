package com.todoapp.service.impl;


import com.todoapp.dto.TaskDto;
import com.todoapp.dto.ToDoDto;
import com.todoapp.entity.ToDoEntity;
import com.todoapp.entity.ToDoTaskEntity;
import com.todoapp.repository.ToDoRepository;
import com.todoapp.repository.ToDoTaskRepository;
import com.todoapp.service.TodoService;
import com.todoapp.todomapper.ToDoMapper;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TodoServiceimpl implements TodoService {

    @Autowired
    private ToDoRepository toDoRepository;

    @Autowired
    private ToDoTaskRepository toDoTaskRepository;




    @Override
    public void createToDo(ToDoDto toDoDto) {
//        ToDoEntity toDoEntity = ToDoMapper.mapToToDoEntity(toDoDto);
        ToDoEntity toDoEntity  = ToDoMapper.mapToToDoEntity(toDoDto);
        toDoRepository.save(toDoEntity);
    }

    @Override
    public ToDoDto findByUsername(String email) {
        ToDoEntity entity = toDoRepository.findByUsername(email);
        return ToDoMapper.mapToToDoDto(entity);
    }

    @Override
    public void createTaskToDo(ToDoTaskEntity toDoTaskEntity) {
        toDoTaskRepository.save(toDoTaskEntity);
    }





    @Override
    public List<TaskDto> findByEmailVal(String strEmailValue) {
        List<ToDoTaskEntity> toDoTaskEntities = toDoTaskRepository.findByEmailVal(strEmailValue);
        System.out.println("Fetched Entities: " + toDoTaskEntities.toString()); // Log fetched entities
        return toDoTaskEntities.stream()
                .map(ToDoMapper::mapToTaskDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<TaskDto> findByEmailValflag(String strEmailValue) {
        List<ToDoTaskEntity> entities = toDoTaskRepository.findByEmailValflag(strEmailValue);
        System.out.println("Fetched Flag Entities: " + entities); // Log fetched entities
        return entities.stream()
                .map(ToDoMapper::mapToTaskDto)
                .collect(Collectors.toList());
    }


    @Override
    public TaskDto findbyId(Long id) {
        ToDoTaskEntity entity = toDoTaskRepository.findById(id).orElseThrow(()->new RuntimeException("Task not found"));
        return ToDoMapper.mapToTaskDto(entity);
    }

    @Override
    public void updateTofinish(TaskDto taskDto) {
        ToDoTaskEntity entity = ToDoMapper.mapToTaskEntity(taskDto);
        toDoTaskRepository.save(entity);
    }




}
