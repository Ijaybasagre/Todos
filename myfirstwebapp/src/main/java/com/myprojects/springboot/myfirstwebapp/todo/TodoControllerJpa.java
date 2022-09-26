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

@Controller
public class TodoControllerJpa {
    private final TodoService service;

    @Autowired
    public TodoControllerJpa(TodoService service) {
        this.service = service;
    }

    @RequestMapping("list-todos")
    public String listAllTodos(ModelMap model){
        var username = getUsername(model);
        var todoList = service.findByUsername(username);
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
        todo.setUsername(username);
        service.addNewTodo(todo);
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
        service.deleteTodo(id);
        return "redirect:list-todos";
    }

    @RequestMapping(value = "update-todo")
    public String gotoUpdateTodo(ModelMap modelMap,@RequestParam Long id){
        var todo = service.findById(id);
        modelMap.addAttribute("todo",todo);
        return "add-Todo";
    }

    @RequestMapping(value = "update-todo",method = RequestMethod.POST)
    public String updateTodo(@Valid Todo todo, BindingResult result,ModelMap modelMap){
        if(result.hasErrors()){
            return "add-Todo";
        }
        String username =  getUsername(modelMap);
        service.updateTodo(username,todo);
        return "redirect:list-todos";
    }

    private String getUsername(ModelMap model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }
}
