package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StudentResult {
    private int sid;
    private String name;
    private String email;
}
