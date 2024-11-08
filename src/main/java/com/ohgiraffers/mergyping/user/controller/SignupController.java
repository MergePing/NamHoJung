package com.ohgiraffers.mergyping.user.controller;

import com.ohgiraffers.mergyping.user.model.dto.SignupDTO;
import com.ohgiraffers.mergyping.user.model.service.SignupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/auth")
public class SignupController {

    @Autowired
    private SignupService signupService;

    @GetMapping("/signupterms")
    public String signupFront() {

        return "user/signup/signupterms";
    }

    @GetMapping("/signup")
    public String signupMain() {

        return "user/signup/signup";
    }


    @PostMapping("/signup")
    public ModelAndView signup(ModelAndView mv, @ModelAttribute SignupDTO signupDTO) {
            int result = signupService.regist(signupDTO);

            String message = "";

            if (result > 0) {
                message = "회원가입이 정상적으로 완료되었습니다.";
                mv.setViewName("redirect:/signupconfirm");
            } else {
                message = "회원가입에 실패하였습니다.";
                mv.setViewName("redirect:/signupterms");
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
        boolean isValid = userPwd.length() >= 8
                && userPwd.matches(".*[a-zA-Z].*")
                && userPwd.matches(".*[0-9].*")
                && userPwd.matches(".*[-_!@#$%^&*()+|{};':.,/?<>].*")
                && !userPwd.matches(".*[가-힣].*")
                && !userPwd.matches(".*[ㄱ-ㅎ].*");

        return isValid;
    }

    @GetMapping("/passwordConfirm")
    @ResponseBody
    public boolean passwordConfirm(@RequestParam String userPwd, @RequestParam String confirmPwd) {
        boolean isValid = userPwd.equals(confirmPwd);

        return isValid;
    }

    @GetMapping("/checkNick")
    @ResponseBody
    public boolean checkNick(@RequestParam String userNick) {
        return signupService.checkNick(userNick);
    }

    @GetMapping("/checkemail")
    public ResponseEntity<Map<String, Boolean>> checkEmail(@RequestParam String userEmail) {
        Boolean isAvailable = signupService.checkEmailAvailability(userEmail);
        Map<String, Boolean> response = new HashMap<>();
        response.put("isAvailable", isAvailable);
        return ResponseEntity.ok(response);
    }
}


