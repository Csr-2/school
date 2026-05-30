package com.test.student.mapper;

import com.test.student.entity.Grades;
import com.test.student.entity.Score;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ScoreMapper {
    @Select("select u.username,s.classname,s.daily,s.exam,s.total from user u inner join scores s on u.username=s.name where u.username =#{username}")
    List<Score> findScoreByNo(String username);
    @Select("select s.id,s.name,s.studentNo,s.daily,s.exam,s.total,s.status,t.classname from teacher t inner join scores s on t.classname=s.classname where t.classname=#{classname}")
    List<Grades> findScoreByClassname(String classname);
    // 1. 根据ID查询 - 返回单个对象
    @Select("SELECT id, name, studentNo, daily, exam, total, status FROM scores WHERE id = #{id}")
    Grades findScoreById(Integer id);
    // 3. 插入数据 - 返回影响行数
    @Insert("INSERT INTO scores(name, studentNo, daily, exam, total, status,classname) " +
            "VALUES(#{name}, #{studentNo}, #{daily}, #{exam}, #{total}, #{status},#{classname})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Grades grades);

    // 4. 更新数据 - 返回影响行数
    @Update("UPDATE scores SET name=#{name}, studentNo=#{studentNo}, " +
            "daily=#{daily}, exam=#{exam}, total=#{total}, status=#{status} WHERE id=#{id}")
    int updateById(Grades grades);

    // 5. 删除数据 - 返回影响行数
    @Delete("DELETE FROM scores WHERE id=#{id}")
    int deleteById(Integer id);
}
