package com.example.todolist.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CreateTodoRequest {
    @NotEmpty(message = "title không được để trống")
    private String title;
}