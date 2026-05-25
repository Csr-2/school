package com.test.student.dao;

import com.test.student.entity.Book;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
//两个值互相匹配，与其他值没有联系
public interface BookMapper {
    @Select("SELECT * FROM books WHERE title LIKE CONCAT('%', #{keyword}, '%') " )

    List<Book> findBooksByKeyword(@Param("keyword") String keyword);
}
