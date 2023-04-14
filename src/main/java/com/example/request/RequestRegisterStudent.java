package com.example.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class RequestRegisterStudent implements Serializable {
    private int sid;
    private String name;
    private String email;
    private String password;
    private String Code;
}
