package com.example.todolist.dao;

import com.example.todolist.modol.Todo;

import java.util.ArrayList;
import java.util.List;

public class TodoData {
    public static List<Todo> listTodo = new ArrayList<>(List.of(
            new Todo(1, "Lam Bai Tap", true),
            new Todo(2, "Da bong", true),
            new Todo(3, "nau com", false),
            new Todo(4, "rua bat", false)
    ));
}
