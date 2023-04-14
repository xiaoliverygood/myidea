package com.example.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Student {
    private int sid;
    private String name;
    private String email;
    private String password;
}
