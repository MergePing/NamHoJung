package com.ohgiraffers.mergyping.level.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LevelController {

    @GetMapping("/level-info")
    public String level() {
        return "/user/level/level";
    }
}