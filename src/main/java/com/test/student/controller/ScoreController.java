package com.test.student.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ScoreController {
    @GetMapping("score")
    public String score() {
        return "score";
    }
}
