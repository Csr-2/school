package com.test.student.entity;

import lombok.Data;

@Data
public class Score {
    private String classname;
    private Double daily;
    private Double exam;
    private Double total;
}
