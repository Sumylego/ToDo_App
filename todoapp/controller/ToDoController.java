package com.todoapp.controller;


import com.todoapp.dto.ToDoDto;
import com.todoapp.entity.ToDoEntity;
import com.todoapp.service.TodoService;
import com.todoapp.todomapper.ToDoMapper;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;

@Controller
public class ToDoController {

    private TodoService todoService;

    public ToDoController(TodoService todoService){

        this.todoService=todoService;
    }


    @GetMapping("/registration/new")
    public String addNewUser(Model model){
        ToDoEntity toDoEntity =new ToDoEntity();
        model.addAttribute("add_user",toDoEntity);

        return "register";
    }

    @PostMapping("/registration")
    public String saveTodoUser(@Valid @ModelAttribute("add_user") ToDoDto toDoDto, BindingResult result, Model model, @RequestParam String email, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("add_user", toDoDto);

            return "register";
        }

        String encodePassword = BCrypt.hashpw(toDoDto.getPassword(), BCrypt.gensalt(12));
        toDoDto.setPassword(encodePassword);

        ToDoDto dbUser = todoService.findByUsername(email);
        System.out.println("Email: " + email);
        String dbEmail = (dbUser != null) ? dbUser.getEmail() : "";
        System.out.println("dbEmail " + dbEmail);

        if (dbUser != null) {
            if (email.equals(dbEmail)) {
                model.addAttribute("emailValid", "Email already exists");
                System.out.println("CHECK111");
                return "register";
            }
        }
//        toDoEntity.setTimestamp(LocalDateTime.now());

        toDoDto.setTimestamp(LocalDateTime.now());
        System.out.println(toDoDto.getTimestamp()+"currentdatetime");
        System.out.println(encodePassword);
//        ToDoEntity toDoEntity = ToDoMapper.mapToToDoEntity(toDoDto);
        todoService.createToDo(toDoDto);



        redirectAttributes.addFlashAttribute("success", "Register successfully, please login");

        return "redirect:/registration/new";

    }


    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("email") String email,
                        @RequestParam("password") String password,
                        Model model,HttpSession session) {
        System.out.println("CHECK");
        System.out.println(email + " email");
        session.setAttribute("emailvalue",email);
        session.getAttribute("emailvalue");
        System.out.println(session.getAttribute("emailvalue")+" getSessionValue");
        System.out.println(password + " password");


        ToDoDto dbUser = todoService.findByUsername(email);


        if (dbUser != null) {



            Boolean isPasswordMatch = BCrypt.checkpw(password, dbUser.getPassword());
            System.out.println("isPasswordMatch " + isPasswordMatch);
            System.out.println("dbUser " + dbUser);
            System.out.println("password " + password);
            System.out.println("dbUser.getPassword() " + dbUser.getPassword());

            if (isPasswordMatch) {

                System.out.println("getEmail "+dbUser.getEmail());
                System.out.println("getName "+dbUser.getName());
                System.out.println("getContact "+dbUser.getEmail());
//                model.addAttribute("useremail",dbUser.getEmail());
                return "todohome";
            } else {
                model.addAttribute("error", "Invalid email or password");
                return "login";
            }
        } else {
            model.addAttribute("error", "Invalid email or password");
            return "login";
        }
    }

    @GetMapping("/profile")
    public String getProfile(Model model, HttpSession session,ToDoDto toDoDto) {
        String email = (String) session.getAttribute("emailvalue");
        System.out.println(email + " getSessionValue");

        ToDoDto dbUser = todoService.findByUsername(email);
        dbUser.setEmail(email);
        String strVar = dbUser.getContact();
        dbUser.setContact(strVar);
        String strVar1 = dbUser.getName();
        dbUser.setName(strVar1);

        System.out.println(dbUser.getContact()+" monk"+dbUser.getName()+" monk2"+dbUser.getEmail());
        if (dbUser != null) {
            model.addAttribute("user", dbUser);
        } else {
            System.out.println("BOOM");
            model.addAttribute("user", new ToDoEntity());
        }

        return "profile";
    }

    @PostMapping("/profile")
    public String updateProfile(@ModelAttribute ToDoDto user, HttpSession session,RedirectAttributes redirectAttributes) {
        String emailSession = (String) session.getAttribute("emailvalue");


        ToDoDto existingUser = todoService.findByUsername(emailSession);

        if (existingUser != null) {

            existingUser.setName(user.getName());
            existingUser.setContact(user.getContact());


            if ("Yes".equals(user.getCheVal()) && !user.getPassword().isEmpty()) {
                String encodePassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(12));
                existingUser.setPassword(encodePassword);
            }
            ToDoEntity updatedEntity = ToDoMapper.mapToToDoEntity(existingUser);
            ToDoDto createUser = ToDoMapper.mapToToDoDto(updatedEntity);

            todoService.createToDo(createUser);
            redirectAttributes.addFlashAttribute("success", "Profile Updated Successfully");
        }

        return "redirect:/profile";
    }



    @GetMapping("/logout")
    public String logout(HttpSession session) {

        session.invalidate();

        return "redirect:/login?logout";
    }



}
