package com.myprojects.springboot.myfirstwebapp.todo;

import com.myprojects.springboot.myfirstwebapp.exception.TodoException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TodoService {

    @Autowired
    private TodoRepository repository;

    public TodoService() {
    }

    public TodoService(TodoRepository repository) {
        this.repository = repository;
    }

    public List<Todo> findByUsername(String username){
        return repository.findByUsername(username);
    }

    public Todo findById(Long id){
        var todo=  repository.findById( id);

        if(todo.isEmpty())
            throw new TodoException("%s not found".formatted(id));

        return todo.get();
    }

    public void addNewTodo(Todo todo){
        repository.save(todo);
    }

    public void deleteTodo(Long id){
        repository.deleteById(id);
    }

    public void updateTodo(String username,@Valid Todo todo){
        todo.setUsername(username);
        repository.save(todo);
    }

}
