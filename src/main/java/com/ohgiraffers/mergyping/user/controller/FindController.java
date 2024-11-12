package com.ohgiraffers.mergyping.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/find")
@Controller
public class FindController {

    @GetMapping("/select")
    public String search() {

        return "/user/searchuser/searchuser";
    }

    @GetMapping("/id")
    public String serachId() {

        return "/user/searchuser/searchId";
    }
}
