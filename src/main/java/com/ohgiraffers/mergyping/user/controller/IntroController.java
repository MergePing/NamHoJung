package com.ohgiraffers.mergyping.user.controller;
import com.ohgiraffers.mergyping.auth.model.AuthDetails;
import com.ohgiraffers.mergyping.common.UserRole;
import com.ohgiraffers.mergyping.user.model.dto.MyPageDTO;
import com.ohgiraffers.mergyping.user.model.service.MyPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

@Controller
public class IntroController {

    @Autowired
    private MyPageService myPageService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/userinfo")
    public String findUserInfo(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof AuthDetails) {
            AuthDetails userDetails = (AuthDetails) authentication.getPrincipal();
            int userNo = userDetails.getUserNo();
            UserRole role = userDetails.getUserRole();

            // MyPageDTO에 userNo를 전달하여 사용자 정보를 가져옵니다.
            MyPageDTO myPageDTO = myPageService.findNickName(userNo);
            model.addAttribute("myPageDTO", myPageDTO);

            MyPageDTO myPageDTO2 = myPageService.findEmail(userNo);
            model.addAttribute("myPageDTO2", myPageDTO2);

            MyPageDTO myPageDTO3 = myPageService.findId(userNo);
            model.addAttribute("myPageDTO3", myPageDTO3);

            MyPageDTO userInfo = myPageService.findUserInfo(userNo);

            model.addAttribute("userInfo", userInfo);

            MyPageDTO level = myPageService.findUserInfo(userNo);
            model.addAttribute("level", level);

            MyPageDTO userName = myPageService.findUserInfo(userNo);
            model.addAttribute("userName", userName);

            MyPageDTO writerNo = myPageService.findUserInfo(userNo);
            model.addAttribute("writerNo", writerNo);

        } else {
            // 인증되지 않은 경우 로그인 페이지로 리다이렉트
            return "redirect:/login";
        }

        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals("ADMIN"));

        if (isAdmin) {
            return "redirect:/admin";
        } else {
            return "user/mypage/userinfo"; // userinfo.html로 이동
        }
    }

    @PostMapping("/userinfo")
    public String modifyUserName(@ModelAttribute MyPageDTO myPageDTO, @RequestParam(required = false) String newPassword) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof AuthDetails) {
            AuthDetails userDetails = (AuthDetails) authentication.getPrincipal();
            int userNo = userDetails.getLoginUserDTO().getUserNo();  // 로그인한 사용자의 userNo 가져오기

            System.out.println("userNo = " + userNo);

            // MyPageDTO에 로그인된 사용자 userNo 설정
            myPageDTO.setUserNo(userNo);
            // 비밀번호가 전달되지 않더라도 닉네임 수정은 항상 진행
            if (myPageDTO.getUserName() != null && !myPageDTO.getUserName().isEmpty()) {
                myPageService.modifyUserName(myPageDTO);  // 닉네임 수정
            }

            // 비밀번호가 전달된 경우 비밀번호 업데이트
            if (newPassword != null && !newPassword.isEmpty()) {
                System.out.println("Before encryption: " + newPassword);
                String userPwd = passwordEncoder.encode(newPassword);
                System.out.println("Encoded password: " + userPwd);// 비밀번호 암호화
                // Map을 사용하여 userNo와 userPwd를 전달
                Map<String, Object> params = new HashMap<>();
                params.put("userNo", userNo);
                params.put("userPwd", userPwd);

                myPageService.updatePassword(params);  // 암호화된 비밀번호로 업데이트

                // 비밀번호 변경 후 세션의 Authentication 정보를 갱신 (새 비밀번호로 로그인)
                if (authentication != null && authentication.getPrincipal() instanceof AuthDetails) {
                    userDetails = (AuthDetails) authentication.getPrincipal();
                    userDetails.getLoginUserDTO().setUserPass(userPwd); // 새 비밀번호로 설정
                    Authentication newAuthentication = new UsernamePasswordAuthenticationToken(userDetails, userPwd, authentication.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(newAuthentication);
                }
            }
        } else {
            return "redirect:/login";  // 인증되지 않은 경우 로그인 페이지로 리다이렉트
        }

        return "redirect:/userinfo";
    }

}
