package com.example.todolist.modol;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Todo {
    private Integer id;
    private String title;
    private Boolean status;
}