package com.example.todolist.service;

import com.example.todolist.exception.BadRequest;
import com.example.todolist.model.Todo;
import com.example.todolist.repository.TodoRepository;
import com.example.todolist.request.CreateTodoRequest;
import com.example.todolist.request.UpdateTodoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {
    @Autowired
    private TodoRepository todoRepository;

    public List<Todo> getAllTodo() {
        return todoRepository.findAll();
    }

    public Todo getTodoById(Integer id) {
        Optional<Todo> todoOptional = todoRepository.findById(id);
        if (todoOptional.isPresent()) {
            return todoOptional.get();
        } else {
            throw new BadRequest("Không tìm thấy id");
        }
    }

    public Todo createTodo(CreateTodoRequest request) {
        Todo todo = Todo.builder()
                .title(request.getTitle())
                .status(false)
                .build();
        todoRepository.save(todo);
        return todo;
    }

    public Todo updateTodo(Integer id, UpdateTodoRequest request) {
        Optional<Todo> todoOptional = todoRepository.findById(id);

        if (todoOptional.isPresent()) {
            Todo todo = todoOptional.get();
            todo.setTitle(request.getTitle());
            todo.setStatus(request.getStatus());

            todoRepository.save(todo);
            return todo;
        } else {
            throw new BadRequest("Không tìm thấy id");
        }
    }

    public void deleteTodo(Integer id) {
        Optional<Todo> todoOptional = todoRepository.findById(id);

        if (todoOptional.isPresent()) {
            todoRepository.delete(todoOptional.get());
        } else {
            throw new BadRequest("Không tìm thấy id");
        }
    }
}
