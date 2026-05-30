package com.test.student.mapper;

import com.test.student.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("select * from user where username=#{username}")
    User findUserByUsername(String username);
}
