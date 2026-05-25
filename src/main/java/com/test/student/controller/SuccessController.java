package com.test.student.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SuccessController {
    // ✅ 添加这个方法，处理

    @GetMapping("/success")
    public String success(){
        return "success";  // 返回 templates
    }
}
