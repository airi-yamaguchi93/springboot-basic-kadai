package com.example.springkadaitodo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.springkadaitodo.entity.ToDo;
import com.example.springkadaitodo.service.ToDoService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ToDoController {

    private final ToDoService toDoService;
    @GetMapping("/todo")
    public String showTodoList(Model model) {
        List<ToDo> todoList = toDoService.findAll();
        model.addAttribute("todoList", todoList);
        return "todoview";
    }
}