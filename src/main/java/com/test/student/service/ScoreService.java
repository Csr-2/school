package com.test.student.service;

import com.test.student.dao.ScoreMapper;
import com.test.student.dao.UserMapper;
import com.test.student.entity.Grades;
import com.test.student.entity.Score;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScoreService {
    @Autowired
    private ScoreMapper scoreMapper;
    @Autowired
    private HttpSession session;
    public void insertScore(Grades grades){
        String classname=(String) session.getAttribute("classname");
        grades.setClassname(classname);
        scoreMapper.insert(grades);
    }
}
