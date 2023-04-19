package com.example.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class RequestLoginStudent implements Serializable {
    private int sid;//通过sid登录
    private String password;
}
