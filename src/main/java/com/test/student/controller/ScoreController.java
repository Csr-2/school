package com.test.student.controller;

import com.test.student.dao.ScoreMapper;
import com.test.student.entity.Score;
import com.test.student.entity.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class ScoreController {
    @Autowired
    ScoreMapper scoreMapper;
    @GetMapping("/score")
    public List<Score> score(HttpSession session) {
        String username= (String) session.getAttribute("username");
        List<Score> doscore =scoreMapper.findScoreByNo(username);
        return doscore;
    }
}
