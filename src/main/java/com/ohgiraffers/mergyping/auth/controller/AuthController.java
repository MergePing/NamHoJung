package com.ohgiraffers.mergyping.auth.controller;

import com.ohgiraffers.mergyping.auth.model.service.AuthService;
import com.ohgiraffers.mergyping.user.model.dto.LoginUserDTO;
import com.ohgiraffers.mergyping.user.model.service.MyPageService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

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

    @GetMapping("/user-info")
    @ResponseBody
    public ResponseEntity<Map<String, String>> getUserInfo(HttpSession session) {
        LoginUserDTO loginUser = (LoginUserDTO) session.getAttribute("loginUser");

        Map<String, String> userInfo = new HashMap<>();
        userInfo.put("userName", loginUser.getUserName());

        return ResponseEntity.ok(userInfo);
    }


}
