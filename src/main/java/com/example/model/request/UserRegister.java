package com.example.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegister {
    private String email;
    private String password;
    private String sex;
    private Integer time;
    private String Code;
}
