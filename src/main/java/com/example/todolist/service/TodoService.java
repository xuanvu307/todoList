package com.example.todolist.service;

import com.example.todolist.exception.NotFoundException;
import com.example.todolist.modol.Todo;
import com.example.todolist.repository.TodoRepository;
import com.example.todolist.request.CreateTodoRequest;
import com.example.todolist.request.UpdateTodoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Random;

@Service
public class TodoService {


    @Autowired
    private TodoRepository todoRepository;

    private Integer genarateId() {
        Random rd = new Random();
        return rd.nextInt(1000);
    }

    public List<Todo> getAllTodo() {
        return todoRepository.getAllTodo();
    }

    public Todo getTodoById(Integer id) {
        return todoRepository.getAllTodo().stream()
                .filter(todo -> todo.getId() == id)
                .findFirst()
                .orElseThrow(() ->
                        new NotFoundException("id khong ton tai")
                )
                ;
    }

    public Todo createTodo(CreateTodoRequest request) {
        Todo todo = Todo.builder()
                .id(genarateId())
                .title(request.getTitle())
                .status(false)
                .build();
        todoRepository.getAllTodo().add(todo);
        return todo;
    }

    public Todo updateTodo(Integer id, UpdateTodoRequest request) {
        for (Todo todo : todoRepository.getAllTodo()) {
            if (todo.getId().equals(id)) {
                todo.setTitle(request.getTitle());
                todo.setStatus(request.getStatus());
                return todo;
            }
        }
        throw new NotFoundException("id khong dung");
    }

    public void deleteTodo(Integer id) {
        todoRepository.getAllTodo().removeIf(todo -> Objects.equals(todo.getId(), id));
    }

    public Todo updateStatusTodo(Integer id) {
        for (Todo todo : todoRepository.getAllTodo()) {
            if (todo.getId().equals(id)) {
                todo.setStatus(!todo.getStatus());
                return todo;
            }
        }
        throw new NotFoundException("id khong dung");
    }
}
