package com.test.student.mapper;

import com.test.student.entity.Teacher;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
@Mapper
public interface TeacherMapper {
    @Select("SELECT * FROM teacher WHERE username = #{username}")
    Teacher findByUsername(String username);
}
