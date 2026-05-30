package com.test.student.controller;

import com.test.student.common.Result;
import com.test.student.mapper.TeacherMapper;
import com.test.student.entity.Teacher;
import com.test.student.entity.User;
import com.test.student.mapper.UserMapper;
import com.test.student.service.MD5Util;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//直接跳转html
@RestController
public class LoginController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private TeacherMapper teacherMapper;
    @PostMapping("doLogin")
    @ResponseBody
    //@RequestParam 是 装HTML的name，当然有ajax时是ajax
    public Result doLogin(@RequestParam(value = "username") String username, @RequestParam(value = "password") String password ,@RequestParam(value = "roleType")String roleType, HttpSession session) {
        // 1. 参数校验
        if (username == null || username.trim().isEmpty() ||
                password == null || password.isEmpty()) {
            return Result.failed("用户名或密码不能为空");
        }
        // 新增：角色校验
        if (roleType == null || roleType.isEmpty()) {
            return Result.failed("请选择学生或教师身份");
        }

        if ("student".equals(roleType)) {
        // 2. 根据用户名查询用户（不要同时用密码查）
        User user = userMapper.findUserByUsername(username);

        // 3. 判断用户是否存在
        if (user == null) {
            return Result.failed("用户不存在");
        }

        String encryptedPasswordFromDB = user.getPassword();

        // 4. 将用户输入的密码加密后，与数据库中的加密密码比较
        String encryptedInputPassword = MD5Util.md5(password);

        if (!encryptedInputPassword.equals(encryptedPasswordFromDB)) {
            return Result.failed("密码错误");
        }

        // 5. 登录成功，保存 session
        session.setAttribute("username", user.getUsername());
        session.setAttribute("role", "student");
        return Result.success("/success","登录成功！");
        }else if ("teacher".equals(roleType)) {
            // === 教师登录逻辑（新增） ===
            Teacher teacher = teacherMapper.findByUsername(username);
            if (teacher == null) {
                return Result.failed("教师账号不存在");
            }
            String encryptedPasswordFromDB = teacher.getPassword();
            String encryptedInputPassword = MD5Util.md5(password);
            if (!encryptedInputPassword.equals(encryptedPasswordFromDB) ){
                return Result.failed("密码错误");
            }

            // 登录成功，保存教师信息到session
            session.setAttribute("username", teacher.getUsername());
            session.setAttribute("classname", teacher.getClassname());
            session.setAttribute("role", "teacher");

            return Result.success("/teacher","教师登录成功");

        } else {
            return Result.failed("无效的身份类型");
        }
    }
}
