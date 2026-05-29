package com.test.student.entity;

import lombok.Data;

@Data
public class Leave {
    private String name;
    private Integer id;
    private String type;
    private String start_date;
    private String end_date;
    private String status;
    private String reason;
    private String apply_time;
}
