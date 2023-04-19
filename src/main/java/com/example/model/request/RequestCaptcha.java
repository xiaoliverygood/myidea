package com.example.model.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class RequestCaptcha implements Serializable {
    private String emailOfCaptcha;
}
