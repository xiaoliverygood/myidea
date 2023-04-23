package com.example.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActivityRequest {
    private String name;
    private Integer time;
    private LocalDateTime beginTime;
    private LocalDateTime lateTime;
    private String location;
    private Integer maxpeople;
}
