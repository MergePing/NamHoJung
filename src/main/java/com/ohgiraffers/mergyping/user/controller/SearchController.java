package com.ohgiraffers.mergyping.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/search")
@Controller
public class SearchController {

    @GetMapping("/select")
    public String search() {

        return "/user/searchuser/searchuser";
    }
}
