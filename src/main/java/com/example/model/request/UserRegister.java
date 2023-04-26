package com.example.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    private Long time;
    @JsonProperty("Code")//json格式的数据是要求骆驼峰形式的
    private String Code;
}
