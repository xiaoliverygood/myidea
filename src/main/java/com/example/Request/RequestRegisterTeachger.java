package com.example.Request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RequestRegisterTeachger {
    private  int tid;
    private String name;
    private String email;
    private String lesson;
    private String password;
}
