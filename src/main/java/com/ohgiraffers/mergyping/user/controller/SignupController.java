package com.ohgiraffers.mergyping.user.controller;

import com.ohgiraffers.mergyping.user.model.dto.SignupDTO;
import com.ohgiraffers.mergyping.user.model.service.SignupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/auth")
public class SignupController {

    @Autowired
    private SignupService signupService;

    @GetMapping("/signupTerms")
    public String signupFront() {

        return "user/signup/signup3";
    }

    @GetMapping("/signup")
    public String signupMain() {

        return "user/signup/signup4";
    }

    @PostMapping("/signup")
    public ModelAndView signup(ModelAndView mv, @ModelAttribute SignupDTO signupDTO) {
            int result = signupService.regist(signupDTO);

            String message = "";

            if (result > 0) {
                message = "회원가입이 정상적으로 완료되었습니다.";
                mv.setViewName("/user/signup/signupConfirm");
            } else {
                message = "회원가입에 실패하였습니다.";
                mv.setViewName("user/signup/signup3");
            }
            mv.addObject("message", message);

        return mv;
    }

    @GetMapping("/checkId")
    @ResponseBody
    public boolean checkId(@RequestParam String userId) {
        return signupService.checkId(userId);
    }

    @GetMapping("/checkPassword")
    @ResponseBody
    public boolean checkPassword(@RequestParam String userPwd) {
        boolean isValid = userPwd.length() > 7
                && userPwd.matches(".*[a-zA-Z].*")
                && userPwd.matches(".*[0-9].*")
                && userPwd.matches(".*[-_!@#$%^&*()+|{};':.,/?<>].*")
                || userPwd.matches(".*[가-힣].*")
                || userPwd.matches(".*[ㄱ-ㅎ].*");

        return isValid;
    }
}
