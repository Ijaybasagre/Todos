package com.myprojects.springboot.myfirstwebapp.todo;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

//@Controller
public class TodoController {

    private final TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @RequestMapping("list-todos")
    public String listAllTodos(ModelMap model){
        var username = getUsername(model);
        var todoList =todoService.findByUsername(username);
        model.addAttribute("todos",todoList);
        return "listTodos";
    }

    @RequestMapping(value = "add-Todo",method = RequestMethod.POST)
    public String addNewTodo(ModelMap modelMap, @Valid Todo todo, BindingResult result){
        if(result.hasErrors()){
            modelMap.put("errorMessage","Missing Field or must be at least 10 characters");
            return "add-Todo";
        }
        String username = getUsername(modelMap);
        todoService.addNewTodo(todo);
        return "redirect:list-todos";
    }

    @RequestMapping("add-Todo")
    public String gotoNewTodo(ModelMap modelMap){
        var username = getUsername(modelMap);
        Todo todo = new Todo(0,username,"",LocalDate.now(),false);
        modelMap.addAttribute("todo",todo);
        return "add-Todo";
    }

    @RequestMapping(value = "delete-todo")
    public String deleteTodo(@RequestParam Long id){
        todoService.deleteTodo(id);
        return "redirect:list-todos";
    }

    @RequestMapping(value = "update-todo")
    public String gotoUpdateTodo(ModelMap modelMap,@RequestParam Long id){
        var todo = todoService.findById(id);
        modelMap.addAttribute("todo",todo);
        return "add-Todo";
    }

    @RequestMapping(value = "update-todo",method = RequestMethod.POST)
    public String updateTodo(@Valid Todo todo,ModelMap modelMap, BindingResult result){
        if(result.hasErrors()){
            return "add-Todo";
        }
        var username = getUsername(modelMap);
        todoService.updateTodo(username,todo);
        return "redirect:list-todos";
    }

    private String getUsername(ModelMap model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }
}
