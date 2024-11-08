package com.ohgiraffers.mergyping.user.controller;

import com.ohgiraffers.mergyping.auth.model.AuthDetails;
import com.ohgiraffers.mergyping.user.model.dto.MainDTO;
import com.ohgiraffers.mergyping.user.model.dto.MyPageDTO;
import com.ohgiraffers.mergyping.user.model.service.MainService;
import com.ohgiraffers.mergyping.user.model.service.MyPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Controller
public class MainController {

    private final MyPageService myPageService;
    private final MainService mainService;
    private final PasswordEncoder passwordEncoder;

    @Autowired public MainController(MyPageService myPageService, MainService mainService, PasswordEncoder passwordEncoder) {
        this.myPageService = myPageService;
        this.mainService = mainService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping({"/", "/main"})
    public String bestPost(Model model) {
        List<MainDTO> bestPostList = mainService.bestPost();
        model.addAttribute("bestPosts", bestPostList);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof AuthDetails) {
            AuthDetails userDetails = (AuthDetails) authentication.getPrincipal();
            int userNo = userDetails.getUserNo();
            System.out.println("userNo = " + userNo);

            // MyPageDTO에 userNo를 전달하여 사용자 정보를 가져옵니다.
            MyPageDTO myPageDTO = myPageService.findNickName(userNo);
            model.addAttribute("myPageDTO", myPageDTO);

            Map<String, Object> mbtiInfo = myPageService.findUserMBTIInfo(userNo);
            model.addAttribute("mbtiInfo", mbtiInfo);

        } else {
            // 인증되지 않은 경우 로그인 페이지로 리다이렉트
            return "redirect:/login";
        }
        return  "main/main";
    }

    // 개인정보 들어갈때 비밀번호 입력
    @PostMapping("/verifyPassword")
    @ResponseBody
    public ResponseEntity<Boolean> verifyPassword(@RequestParam String inputPassword) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof AuthDetails) {
            AuthDetails userDetails = (AuthDetails) authentication.getPrincipal();
            String storedPasswordHash = userDetails.getPassword();  // 암호화된 비밀번호 가져오기

            // 사용자가 입력한 비밀번호를 암호화하여 비교
            boolean isPasswordCorrect = passwordEncoder.matches(inputPassword, storedPasswordHash);

            return ResponseEntity.ok(isPasswordCorrect); // 비밀번호가 맞으면 true, 아니면 false 반환
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);
        }
    }
    @PostMapping("/attendenceCheck")
    @ResponseBody
    public String checkAttendance() {
        // 현재 로그인한 유저 정보 가져오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof AuthDetails) {
            AuthDetails userDetails = (AuthDetails) authentication.getPrincipal();
            int userNo = userDetails.getUserNo();

            // 오늘 날짜 확인
            LocalDate today = LocalDate.now();
            String todayStr = today.toString();  // "YYYY-MM-DD" 형식

            // 출석 체크 여부 확인
            boolean hasCheckedToday = myPageService.hasCheckedToday(userNo, todayStr);
            if (hasCheckedToday) {
                return "이미 출석을 체크하셨습니다.";  // 이미 출석한 경우
            }

            // 출석 체크 등록
            myPageService.checkAttendance(userNo, todayStr);

            // 누적 출석 수 증가
            myPageService.incrementAttendanceCount(userNo);

            return "출석 체크가 완료되었습니다.";
        }
        return "로그인 정보가 없습니다.";
    }


    @GetMapping("/intro")
    public String intro(){return "/main/intro/intro";}

}
