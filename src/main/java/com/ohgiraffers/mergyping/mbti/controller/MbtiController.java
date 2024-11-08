package com.ohgiraffers.mergyping.mbti.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mbti")
public class MbtiController {

    @GetMapping()
    public String mbtiTest() {

        return "/mbti/mbtistart";
    }
}
