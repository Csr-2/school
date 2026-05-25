package com.test.student.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChooseController {
    @GetMapping("/choose")
    public String choose(){
        return "/choose";
    }
}
