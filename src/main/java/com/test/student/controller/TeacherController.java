package com.test.student.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TeacherController {
    @GetMapping("/teacher")
    public String teacher()
    {
        return "teacher";
    }
}
