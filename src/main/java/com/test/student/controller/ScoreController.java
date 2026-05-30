package com.test.student.controller;

import com.test.student.mapper.ScoreMapper;
import com.test.student.entity.Score;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
