package com.example.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyActivityNoBegin {
    private int id;
    private Date beginTime;
    private Date lateTime;
}
