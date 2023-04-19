package com.example.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Teacher {
    private int tid;
    private String name;
    private String email;
    private String lesson;
    private String password;
}
