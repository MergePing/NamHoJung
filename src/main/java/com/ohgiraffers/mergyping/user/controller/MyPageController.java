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

    @GetMapping("/mypage")
    public String findNickName(Model model) {
        MyPageDTO myPageDTO = myPageService.findNickName();
        model.addAttribute("myPageDTO", myPageDTO);

        return "user/mypage/user";
    }

    @GetMapping("/userinfo")
    public String showUserInfo(Model model) {
        // 사용자 정보를 모델에 추가하는 로직
        // model.addAttribute("userInfo", userInfo);
        return "user/mypage/userinfo"; // userinfo.html로 이동
    }


}
