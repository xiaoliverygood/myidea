package com.example.Request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RequestRegisterStudent {
    private int sid;
    private String name;
    private String email;
    private String password;
}
