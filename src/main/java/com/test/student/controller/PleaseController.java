package com.test.student.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PleaseController {
    @GetMapping("/please")
    public String please() {
        return "please";
    }
}
