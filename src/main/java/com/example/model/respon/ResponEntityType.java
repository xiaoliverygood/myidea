package com.example.model.respon;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponEntityType {
    private String email;
    private String password;
    private String type;
}
