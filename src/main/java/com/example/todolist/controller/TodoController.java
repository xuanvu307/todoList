package com.example.todolist.controller;

import com.example.todolist.modol.Todo;
import com.example.todolist.request.CreateTodoRequest;
import com.example.todolist.request.UpdateTodoRequest;
import com.example.todolist.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api")
public class TodoController {
    @Autowired
    private TodoService todoService;


    @GetMapping("todos")
    public List<Todo> getAllTodo() {
        return todoService.getAllTodo();
    }

    @GetMapping("/todos/{id}")
    public ResponseEntity<?> getTodoById(@PathVariable Integer id) {
        Todo todo = todoService.getTodoById(id);
        return ResponseEntity.ok(todo);
    }

    @PostMapping("todos")
    public Todo creatTodo(@RequestBody CreateTodoRequest request) {
        return todoService.createTodo(request);
    }

    @PutMapping("/todos/{id}")
    public Todo updateTodo(@PathVariable Integer id, @RequestBody UpdateTodoRequest request) {
        return todoService.updateTodo(id, request);
    }

    @GetMapping ("/todos/toggle/{id}")
    public Todo updateStatusTodo(@PathVariable Integer id) {
        return todoService.updateStatusTodo(id);
    }

    @DeleteMapping("/todos/{id}")
    public void deleteTodo(@PathVariable Integer id) {
        todoService.deleteTodo(id);
    }
}
