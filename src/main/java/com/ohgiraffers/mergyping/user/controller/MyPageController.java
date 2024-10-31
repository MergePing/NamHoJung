package com.ohgiraffers.mergyping.user.controller;

import com.ohgiraffers.mergyping.user.model.service.MyPageService;
import com.ohgiraffers.mergyping.user.model.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class MyPageController {
    private final MyPageService myPageService;
    public MyPageController(MyPageService myPageService) {
        this.myPageService = myPageService;

    }
    

    @GetMapping("/user/mypage/user")
    public String findNickName(Model model) {
        String nickname = myPageService.findNickName();
        System.out.println("nickname = " + nickname);
        model.addAttribute("nickname", nickname);
        return "/user/mypage/user";
    }
}
