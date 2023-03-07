package com.example.todolist.repository;

import com.example.todolist.dao.TodoData;
import com.example.todolist.modol.Todo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TodoRepository {

    public List<Todo> getAllTodo(){
        return TodoData.listTodo;
    }
}
