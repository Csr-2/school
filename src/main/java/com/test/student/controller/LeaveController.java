package com.test.student.controller;

import com.test.student.common.Result;
import com.test.student.mapper.LeaveMapper;
import com.test.student.entity.Leave;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LeaveController {
    @Autowired
    private LeaveMapper leaveMapper;
    @GetMapping("/leave")
    public Result<List<Leave>> leave() {
        try {
            List<Leave> leaves = leaveMapper.selectAll();
            return Result.success(leaves);
        } catch (Exception e) {
            return Result.failed("查询失败: " + e.getMessage());
        }
    }
}
