package com.ohgiraffers.mergyping.mbti.controller;

import com.ohgiraffers.mergyping.mbti.model.dto.QuestionDTO;
import com.ohgiraffers.mergyping.mbti.model.service.MbtiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/mbti")
public class MbtiController {

    @Autowired
    private MbtiService mbtiService;

    @GetMapping()
    public String mbtiStart() {

        return "/mbti/mbtistart";
    }

    @GetMapping("/question/{questionNo}")
    @ResponseBody
    public QuestionDTO getQuestion(@PathVariable("questionNo") int questionNo) {
        // 문제 번호에 해당하는 문제를 DB에서 가져와 DTO로 반환
        return mbtiService.getQuestionByNo(questionNo);
    }
}
