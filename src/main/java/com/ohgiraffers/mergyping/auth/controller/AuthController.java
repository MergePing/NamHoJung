package com.ohgiraffers.mergyping.auth.controller;

import com.ohgiraffers.mergyping.auth.model.service.AuthService;
import com.ohgiraffers.mergyping.user.model.dto.LoginUserDTO;
import com.ohgiraffers.mergyping.user.model.service.MyPageService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/auth") // 경로
public class AuthController {

    @GetMapping("/login")
    public void login() {}

    @GetMapping("/fail")
    public ModelAndView loginFail(ModelAndView mv, @RequestParam String message) {
        mv.addObject("message", message);
        mv.setViewName("/auth/fail");

        System.out.println(mv);
        return mv;
    }

    @GetMapping("/verify")
    public String verify(@RequestParam String code) {


        return "이메일 인증이 완료되었습니다.";
    }

}
