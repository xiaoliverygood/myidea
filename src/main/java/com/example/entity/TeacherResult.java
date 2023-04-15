package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TeacherResult {
    private int tid;
    private String name;
    private String email;
    private String lesson;
}
