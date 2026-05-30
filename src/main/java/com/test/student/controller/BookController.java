package com.test.student.controller;

import com.test.student.mapper.BookMapper;
import com.test.student.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class BookController {
    @Autowired
    BookMapper bookMapper;
    @GetMapping("/book")
    public String book(){
        return "book";
    }
    @GetMapping("book/borrow")
    public String borrow(){
        return "borrow";
    }
    @GetMapping("/book/list")
    @ResponseBody  // 返回 JSON
    //@RequestParam里bookName是params:{bookName:keyword}
    public List<Book> getBookList(@RequestParam(value = "bookName" , required = false) String bookName) {
        return bookMapper.findBooksByKeyword(bookName);
    }
}
