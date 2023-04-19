package com.example.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class RequestLoginTeachger implements Serializable {
    private int tid;
    private String password;
}
