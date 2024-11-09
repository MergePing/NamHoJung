package com.ohgiraffers.mergyping.mbti.controller;

import com.ohgiraffers.mergyping.mbti.model.service.MbtiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping("/mbti")
public class MbtiController {

    @Autowired
    private MbtiService mbtiService;

    @GetMapping()
    public String mbtiStart() {

        return "/mbti/mbtistart";
    }

    @GetMapping("/test")
    public String getTestPage(Map<String, String> parameter) {

        int 
    }
}
