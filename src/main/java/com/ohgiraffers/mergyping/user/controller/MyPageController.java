package com.ohgiraffers.mergyping.user.controller;

import com.ohgiraffers.mergyping.user.model.dto.MyPageDTO;
import com.ohgiraffers.mergyping.user.model.service.MyPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyPageController {

    private final MyPageService myPageService;

    @Autowired
    public MyPageController(MyPageService myPageService) {
        this.myPageService = myPageService;
    }

    @GetMapping("/userinfo")
    public String showUserInfo(Model model) {
        MyPageDTO myPageDTO = myPageService.findNickName();
        model.addAttribute("myPageDTO", myPageDTO);

//        MyPageDTO myPageDTO2 = myPageService.findEmail();
//        model.addAttribute("myPageDTO2", myPageDTO2);
        return "user/mypage/userinfo"; // userinfo.html로 이동
    }

    @GetMapping("/useractive")
    public String findNickName(Model model) {
        MyPageDTO myPageDTO = myPageService.findNickName();
        model.addAttribute("myPageDTO", myPageDTO);

        return "user/mypage/useractive";
    }

}
