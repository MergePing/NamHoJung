package com.ohgiraffers.mergyping.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @RequestMapping(value = {"/","/main"})
    public String main() {
        return "/main/main";
    }

    @GetMapping("/intro/intro.html")
    public String intro(){return "/main/intro/intro";}

    @GetMapping("/notice/notice.html")
    public String notice(){return "/notice/notice";}
}
