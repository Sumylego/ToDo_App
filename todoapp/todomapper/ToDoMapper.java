package com.todoapp.todomapper;

import com.todoapp.dto.TaskDto;
import com.todoapp.dto.ToDoDto;
import com.todoapp.entity.ToDoEntity;
import com.todoapp.entity.ToDoTaskEntity;

public class ToDoMapper {

    public static ToDoDto mapToToDoDto(ToDoEntity toDoEntity) {
        if (toDoEntity == null) {
            return null;
        }
        ToDoDto toDoDto =  new ToDoDto(
                toDoEntity.getId(),
                toDoEntity.getName(),
                toDoEntity.getEmail(),

                toDoEntity.getContact(),
                toDoEntity.getPassword(),
                toDoEntity.getTimestamp(),
                toDoEntity.getCheVal()

        );
        return toDoDto;
    }

    public static ToDoEntity mapToToDoEntity(ToDoDto toDoDto) {
        if (toDoDto == null) {
            return null;
        }
        ToDoEntity toDoEntity =  new ToDoEntity(
                toDoDto.getId(),
                toDoDto.getName(),

                toDoDto.getEmail(),
                toDoDto.getContact(),
                toDoDto.getPassword(),
                toDoDto.getTimestamp(),
                toDoDto.getCheVal()
        );
        return toDoEntity;
    }

    public static TaskDto mapToTaskDto(ToDoTaskEntity toDoTaskEntity){

        if (toDoTaskEntity == null) {
            return null; // This should be handled properly, maybe throw an exception or log an error
        }
        return new TaskDto(
                toDoTaskEntity.getId(),
                toDoTaskEntity.getTodotask(),
                toDoTaskEntity.getEmail_todomst(),
                toDoTaskEntity.getTaskDateTime(),
                toDoTaskEntity.getFinishFlag_tmst(),
                toDoTaskEntity.getFinishDate()
        );
    }

    public static ToDoTaskEntity mapToTaskEntity(TaskDto taskDto){
        ToDoTaskEntity toDoTaskEntity = new ToDoTaskEntity(
                taskDto.getId(),
                taskDto.getTodotask(),
                taskDto.getEmail_todomst(),
                taskDto.getTaskDateTime(),
                taskDto.getFinishFlag_tmst(),
                taskDto.getFinishDate()
        );
        return toDoTaskEntity;
    }
}

