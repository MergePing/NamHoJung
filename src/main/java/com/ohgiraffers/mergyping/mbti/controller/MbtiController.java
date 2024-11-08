package com.ohgiraffers.mergyping.mbti.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MbtiController {

    @GetMapping("/mbti")
    public String mbtiTest() {

        return "/mbti/mbtistart";
    }
}
