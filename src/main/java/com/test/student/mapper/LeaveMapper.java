package com.test.student.mapper;

import com.test.student.entity.Leave;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface LeaveMapper {
@Select("select id,name,type,start_date,end_date,reason,apply_time,status from `leave`")
List<Leave> selectAll();
@Insert("insert into `leave`(name,type,start_date,end_date,reason,apply_time,status)" +
        " values(#{name},#{type},#{start_date},#{end_date},#{reason},#{apply_time},#{status})")
    int insert(Leave leave);
}
