package com.test.student.controller;

import com.test.student.common.Result;
import com.test.student.dao.ScoreMapper;
import com.test.student.entity.Grades;
import com.test.student.service.ScoreService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/grades")
public class GradesController {

    @Autowired
    private ScoreMapper scoreMapper;
    @Autowired
    private ScoreService scoreService;
    // 查询所有成绩
    @GetMapping
    public Result<List<Grades>> getAllGrades(HttpSession session) {
        try {
            List<Grades> gradesList = scoreMapper.findScoreByClassname(session.getAttribute("classname").toString());
            return Result.success(gradesList);
        } catch (Exception e) {
            return Result.failed("查询失败: " + e.getMessage());
        }
    }

    // 根据ID查询
    @GetMapping("/{id}")
    public Result<Grades> getGradeById(@PathVariable Integer id) {
        try {
            Grades grade = (Grades) scoreMapper.findScoreById(id);
            if (grade == null) {
                return Result.failed("成绩不存在" );
            }
            return Result.success(grade);
        } catch (Exception e) {
            return Result.failed("查询失败: " + e.getMessage());
        }
    }

    // 添加成绩
    @PostMapping
    public Result<Grades> addGrades(@RequestBody Grades grades) {
        try {

            // 插入数据
            int result = scoreMapper.insert(grades);
            scoreService.insertScore(grades);
            if (result > 0) {
                return Result.success(grades);
            } else {
                return Result.failed("添加失败");
            }
        } catch (Exception e) {
            return Result.failed("添加错误: " + e.getMessage());
        }
    }

    // 编辑成绩
    @PutMapping("/{id}")
    public Result<Grades> editGrades(@PathVariable Integer id, @RequestBody Grades grades) {
        try {
            // 检查是否存在
            Grades existingGrade = (Grades) scoreMapper.findScoreById(id);
            if (existingGrade == null) {
                return Result.failed( "成绩不存在");
            }
            grades.setId(id);
            // 更新数据
            int result = scoreMapper.updateById(grades);

            if (result > 0) {
                Grades updatedGrade = (Grades) scoreMapper.findScoreById(id);
                return Result.success(updatedGrade);
            } else {
                return Result.failed("更新失败");
            }
        } catch (Exception e) {
            return Result.failed("更新错误: " + e.getMessage());
        }
    }

    // 删除成绩
    @DeleteMapping("/{id}")
    public Result<List<Grades>> deleteGrades(@PathVariable Integer id,HttpSession session) {
        try {
            // 检查是否存在
            Grades grade = (Grades) scoreMapper.findScoreById(id);
            if (grade == null) {
                return Result.failed("成绩不存在");
            }

            // 删除数据
            int result = scoreMapper.deleteById(id);

            if (result > 0) {
                // 返回删除后的所有数据
                List<Grades> remainingGrades = scoreMapper.findScoreByClassname( session.getAttribute("classname").toString());
                return Result.success(remainingGrades);
            } else {
                return Result.failed("删除失败");
            }
        } catch (Exception e) {
            return Result.failed("删除错误: " + e.getMessage());
        }
    }
}