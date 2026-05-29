package com.test.student.entity;

import lombok.Data;

@Data
public class Grades {
    private String classname;
    private Integer id;
    private String name;
    private Integer studentNo;
    private Double daily;
    private Double exam;
    private Double total;
    private String status;
}
