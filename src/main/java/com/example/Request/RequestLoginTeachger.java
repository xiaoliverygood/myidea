package com.example.Request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RequestLoginTeachger {
    private int tid;
    private String password;
}
