package com.example.todolist.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UpdateTodoRequest {
    private String title;
    private Boolean status;
}
