package com.test.student.controller;

import com.test.student.common.Result;
import com.test.student.mapper.TeacherMapper;
import com.test.student.entity.Teacher;
import com.test.student.entity.User;
import com.test.student.mapper.UserMapper;
import com.test.student.service.MD5Util;
import com.test.student.service.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private TeacherMapper teacherMapper;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("doLogin")
    @ResponseBody
    public Result doLogin(@RequestParam(value = "username") String username, @RequestParam(value = "password") String password ,@RequestParam(value = "roleType")String roleType) {
        if (username == null || username.trim().isEmpty() ||
                password == null || password.isEmpty()) {
            return Result.failed("用户名或密码不能为空");
        }
        if (roleType == null || roleType.isEmpty()) {
            return Result.failed("请选择学生或教师身份");
        }

        if ("student".equals(roleType)) {
            User user = userMapper.findUserByUsername(username);
            if (user == null) {
                return Result.failed("用户不存在");
            }
            String encryptedPasswordFromDB = user.getPassword();
            String encryptedInputPassword = MD5Util.md5(password);
            if (!encryptedInputPassword.equals(encryptedPasswordFromDB)) {
                return Result.failed("密码错误");
            }
            String token = jwtUtil.generateToken(user.getUsername(), "student");
            Map<String, Object> data = new HashMap<>();
            data.put("token", token);
            data.put("redirectUrl", "/success");
            return Result.success(data, "登录成功！");

        } else if ("teacher".equals(roleType)) {
            Teacher teacher = teacherMapper.findByUsername(username);
            if (teacher == null) {
                return Result.failed("教师账号不存在");
            }
            String encryptedPasswordFromDB = teacher.getPassword();
            String encryptedInputPassword = MD5Util.md5(password);
            if (!encryptedInputPassword.equals(encryptedPasswordFromDB)) {
                return Result.failed("密码错误");
            }
            String token = jwtUtil.generateToken(teacher.getUsername(), "teacher");
            Map<String, Object> data = new HashMap<>();
            data.put("token", token);
            data.put("redirectUrl", "/teacher");
            return Result.success(data, "教师登录成功");

        } else {
            return Result.failed("无效的身份类型");
        }
    }
}
