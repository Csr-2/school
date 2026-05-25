package com.test.student.controller;

import com.test.student.common.Result;
import com.test.student.entity.User;
import com.test.student.dao.UserMapper;
import com.test.student.service.MD5Util;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

//直接跳转html
@RestController
public class LoginController {
    @Autowired
    private UserMapper userMapper;
    @PostMapping("doLogin")
    @ResponseBody
    //@RequestParam 是 装HTML的name，当然有ajax时是ajax
    public Result doLogin(@RequestParam(value = "username") String username, @RequestParam(value = "password") String password , HttpSession session) {
        // 1. 参数校验
        if (username == null || username.trim().isEmpty() ||
                password == null || password.isEmpty()) {
            return Result.failed("用户名或密码不能为空");
        }

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
        return Result.success("登录成功！");
    }
}
