package com.example.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class RequestRegisterTeachger implements Serializable {
    private  int tid;
    private String name;
    private String email;
    private String lesson;
    private String password;
    private String Code;
}
