package com.ohgiraffers.mergyping.user.controller;

import com.ohgiraffers.mergyping.auth.model.AuthDetails;
import com.ohgiraffers.mergyping.user.model.dto.MyPageDTO;
import com.ohgiraffers.mergyping.user.model.dto.MyPagePostDTO;
import com.ohgiraffers.mergyping.user.model.service.MyPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MyPageController {

    private final MyPageService myPageService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public MyPageController(MyPageService myPageService, PasswordEncoder passwordEncoder) {
        this.myPageService = myPageService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/userinfo")
    public String findUserInfo(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof AuthDetails) {
            AuthDetails userDetails = (AuthDetails) authentication.getPrincipal();
            int userNo = userDetails.getUserNo();
            System.out.println("userNo = " + userNo);

            // MyPageDTO에 userNo를 전달하여 사용자 정보를 가져옵니다.
            MyPageDTO myPageDTO = myPageService.findNickName(userNo);
            model.addAttribute("myPageDTO", myPageDTO);

            MyPageDTO myPageDTO2 = myPageService.findEmail(userNo);
            model.addAttribute("myPageDTO2", myPageDTO2);

        } else {
            // 인증되지 않은 경우 로그인 페이지로 리다이렉트
            return "redirect:/login";
        }

        return "user/mypage/userinfo"; // userinfo.html로 이동
    }

    @PostMapping("/userinfo")
    public String modifyUserName(@ModelAttribute MyPageDTO myPageDTO,@RequestParam(required = false) String newPassword) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof AuthDetails) {
            AuthDetails userDetails = (AuthDetails) authentication.getPrincipal();
            int userNo = userDetails.getLoginUserDTO().getUserNo();  // 로그인한 사용자의 userNo 가져오기
            // MyPageDTO에 로그인된 사용자 userNo 설정
            myPageDTO.setUserNo(userNo);
            myPageService.modifyUserName(myPageDTO);

            // 비밀번호가 전달된 경우 비밀번호 업데이트
            if (newPassword != null && !newPassword.isEmpty()) {
                String encodedPassword = passwordEncoder.encode(newPassword);  // 비밀번호 암호화
                myPageService.updatePassword(userNo, encodedPassword);  // 암호화된 비밀번호로 업데이트
            }
        } else {
            return "redirect:/login";  // 인증되지 않은 경우 로그인 페이지로 리다이렉트
        }

        return "redirect:/userinfo";
    }



    @GetMapping("/useractive")
    public String findNickName(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof AuthDetails) {
            AuthDetails userDetails = (AuthDetails) authentication.getPrincipal();
            int userNo = userDetails.getUserNo();
            System.out.println("userNo = " + userNo);

            // MyPageDTO에 userNo를 전달하여 사용자 정보를 가져옵니다.
            MyPageDTO myPageDTO = myPageService.findNickName(userNo);
            model.addAttribute("myPageDTO", myPageDTO);

            List<MyPagePostDTO> writtenPostList = myPageService.findWrittenPost(userNo);
            model.addAttribute("writtenPostList", writtenPostList);

        } else {
            // 인증되지 않은 경우 로그인 페이지로 리다이렉트
            return "redirect:/login";
        }

        return "user/mypage/useractive";
    }

    //닉네임 중복
    @GetMapping("/checknickname")
    public ResponseEntity<Map<String, Boolean>> checkNickname(@RequestParam String nickname) {
        boolean isDuplicate = myPageService.isNicknameDuplicate(nickname);
        Map<String, Boolean> response = new HashMap<>();
        response.put("isDuplicate", isDuplicate);
        return ResponseEntity.ok(response);
    }






}
