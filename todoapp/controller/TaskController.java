package com.todoapp.controller;

import com.todoapp.dto.TaskDto;
import com.todoapp.entity.ToDoTaskEntity;
import com.todoapp.service.TodoService;

import com.todoapp.todomapper.ToDoMapper;
import jakarta.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Controller
public class TaskController {

    private TodoService todoService;

    public TaskController(TodoService todoService){
        this.todoService=todoService;
    }


    @GetMapping("/addtask")
    public String showAddTaskForm(Model model) {

        model.addAttribute("task", new ToDoTaskEntity());
        return "todohome";
    }


    @PostMapping("/addtask")
    public String addTask(TaskDto taskDto, Model model,HttpSession session) {

//        String strEmail = (String) session.getAttribute("useremail");
//        System.out.println("strEmail "+strEmail);
//        System.out.println("useremail1 "+useremail);

//        System.out.println("useremail "+useremail);
//        session.setAttribute("useremailval",useremail);

        String strEmailValue = (String) session.getAttribute("emailvalue");
        System.out.println("strEmailValue "+strEmailValue);
        System.out.println(session.getAttribute("emailvalue")+" getSessionValue");
                taskDto.setEmail_todomst(strEmailValue);

//
        LocalTime currentTime = LocalTime.now();


        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("HH:mm:ss");


        String timeString = currentTime.format(formatter1);

        LocalDateTime now = LocalDateTime.now();


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");


        String str = now.format(formatter);

        System.out.println("Formatted LocalDateTime: " + str);
        System.out.println("getDateTime LocalDateTime: " + taskDto.getTaskDateTime());
        String l_strdateTime = taskDto.getTaskDateTime();

        if(l_strdateTime.equals("")){

            taskDto.setTaskDateTime(str);

        }else {

            taskDto.setTaskDateTime(l_strdateTime);
        }
//        taskDto.setTaskDateTime(str);//hr@jforcesolutions.com
        taskDto.setFinishFlag_tmst("N");
        System.out.println(taskDto.getFinishFlag_tmst()+" taskDto.getFinishFlag_tmst()");

        ToDoTaskEntity toDoTaskEntity = ToDoMapper.mapToTaskEntity(taskDto);

        todoService.createTaskToDo(toDoTaskEntity);
        model.addAttribute("tododata",toDoTaskEntity);

        System.out.println("Chetan21"+taskDto.getEmail_todomst());
        System.out.println("Chetan22"+taskDto.getId());
        System.out.println("Chetan23"+taskDto.getTaskDateTime());

        return "todohome";
    }

    @GetMapping("/todolist")
    public String todoListView(Model model, HttpSession session) {
        String strEmailVal = (String) session.getAttribute("emailvalue");

        System.out.println("strEmailVal: " + strEmailVal);


        List<TaskDto> tasks = todoService.findByEmailVal(strEmailVal);

        model.addAttribute("todotask", tasks);

        System.out.println("TasksLIST: " + tasks);

        return "todolist";
    }
    @GetMapping("/finishlist")
    public String finishListView(Model model, HttpSession session) {
        String strEmailVal = (String) session.getAttribute("emailvalue");
        System.out.println("strEmailVal: " + strEmailVal);

        List<TaskDto> tasks = todoService.findByEmailValflag(strEmailVal);

        model.addAttribute("todotask", tasks);
        return "finishlist";
    }

    @PostMapping("/finishTask/{id}")
    public String finishTask(@PathVariable Long id) {
        TaskDto task = todoService.findbyId(id);
        System.out.println(id+" checkId");
        if (task != null) {
            task.setFinishFlag_tmst("Y");
            LocalDateTime now = LocalDateTime.now();


            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");


            String str = now.format(formatter);
            System.out.println("LocalDateStr "+str);
            System.out.println("TaskDateAndTime "+task.getTaskDateTime());
            if(task.getTaskDateTime().equals(str)) {
                task.setFinishDate(str);
            }else {
                task.setFinishDate(task.getTaskDateTime());
            }
            todoService.updateTofinish(task);
        }
        return "redirect:/finishlist";
    }


}
