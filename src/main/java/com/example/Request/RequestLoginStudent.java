package com.example.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.ibatis.annotations.Delete;

@Data
@AllArgsConstructor
public class RequestLoginStudent {
    private int sid;//通过sid登录
    private String password;
}
