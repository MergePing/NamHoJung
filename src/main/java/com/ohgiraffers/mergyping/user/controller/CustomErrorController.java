package com.ohgiraffers.mergyping.user.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController {
    @RequestMapping("/error")
    public String handleError(Model model) {
        model.addAttribute("errorMessage", "Something went wrong!");
        return "user/error/server";  // 에러 페이지 뷰 이름
    }
}
