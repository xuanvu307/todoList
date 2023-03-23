package com.example.todolist.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "todo_list")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Column
    private String title;

    @Column
    private Boolean status;
}