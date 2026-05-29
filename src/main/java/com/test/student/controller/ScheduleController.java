package com.test.student.controller;

import com.test.student.common.Result;
import com.test.student.dao.BIaoMapper;
import com.test.student.entity.Biaoge;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
@Controller
public class ScheduleController {
    @Autowired
    private BIaoMapper biaoMapper;
    @GetMapping("/schedule")
    //@RequestParam值为name值
    public String schedule(HttpSession session, Model model,@RequestParam(required = false) Integer class_id,@RequestParam(required = false) Integer week) {
        System.out.println("class_id:"+class_id);
        //List [1,2,3] Map{value,key}
        List<Map<String,Object>> courses=biaoMapper.findScheduleByClassId(class_id);
        String[][] tableData=new String[6][6];
        for (Map<String,Object> course:courses){
            //打印
            System.out.println("原始数据: " + course.toString());

            String periodStr = course.get("period").toString();
            String weekDayStr = course.get("week_day").toString();
            int weekday = 0;
            switch (weekDayStr) {
                case "星期一": weekday = 1; break;
                case "星期二": weekday = 2; break;
                case "星期三": weekday = 3; break;
                case "星期四": weekday = 4; break;
                case "星期五": weekday = 5; break;
            }
            int period = Integer.parseInt(periodStr.replace("第", "").replace("节", ""));
            String course_name= course.get("course_name").toString();
            int start_week = Integer.parseInt(course.get("start_week").toString());
            int end_week = Integer.parseInt(course.get("end_week").toString());
            if (week>=start_week && week<=end_week) {
                tableData[period][weekday]=course_name;
            }

        }

        System.out.println("========== 开始打印 tableData ==========");
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                if (tableData[i][j] != null) {
                    System.out.println("tableData[" + i + "][" + j + "] = " + tableData[i][j]);
                }
            }
        }
        System.out.println("========== 打印结束 ==========");

        model.addAttribute("tableData", tableData);
        model.addAttribute("username", session.getAttribute("user"));
        return "schedule";
    }
    @PostMapping("/add")
    @ResponseBody
    public Result<Biaoge> addscore(@RequestBody Biaoge biaoge,HttpSession session) {
        System.out.println("biaoge:"+biaoge);
        try {
            Biaoge result=biaoMapper.addSchedule(biaoge);
            session.setAttribute("result",result);
            return Result.success(result);
        }catch (Exception e){
            return Result.failed("失败"+e.getMessage());
        }

    }
}
