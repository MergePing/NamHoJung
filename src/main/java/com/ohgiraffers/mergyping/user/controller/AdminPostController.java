package com.ohgiraffers.mergyping.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminPostController {

    @GetMapping("/admin/post")
    public String adminPosts() {

        return "user/admin/adminpost";
    }
}
