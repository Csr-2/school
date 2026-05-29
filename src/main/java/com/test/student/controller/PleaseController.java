package com.test.student.controller;

import com.test.student.common.Result;
import com.test.student.dao.LeaveMapper;
import com.test.student.entity.Leave;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PleaseController {
    @Autowired
    LeaveMapper leaveMapper;
    @PostMapping("please")
    public Result<Leave>please(@RequestBody Leave leave) {
        int result=leaveMapper.insert(leave);
        if(result>0){
            return Result.success(leave);
        }
        return Result.failed("失败");
    }
}
