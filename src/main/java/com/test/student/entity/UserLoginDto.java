package com.test.student.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserLoginDto{
    @NotBlank(message = "不能为空")
    @Size(min = 2,max = 20,message = "用户长度2-20之间")
    private String username;
    @NotBlank(message = "不能为空")
    @Size(min = 5,max = 20,message = "用户长度6-20之间")
    private String password;
}
