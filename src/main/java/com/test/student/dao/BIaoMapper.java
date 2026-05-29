package com.test.student.dao;

import com.test.student.entity.Biaoge;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface BIaoMapper {
    @Select("select week_day,period,course_name,start_week,end_week from schedule where class_id=#{class_id}")
    List<Map<String,Object>> findScheduleByClassId(@Param("class_id") Integer class_id);
    @Insert("insert into schedule(week_day,period,course_name,start_week,end_week,class_id)" +
            "values (#{week_day},#{period},#{course_name},#{start_week},#{end_week},#{class_id})")
    Biaoge addSchedule(Biaoge biaoge);

}
