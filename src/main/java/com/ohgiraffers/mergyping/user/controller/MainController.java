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
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
import java.util.Collections;
import java.util.HashMap;
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

    @GetMapping({"/", "/main", "/mypage"})
    public String bestPost(Model model) {
        List<MainDTO> bestPostList = mainService.bestPost();
        model.addAttribute("bestPosts", bestPostList);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof AuthDetails) {
            AuthDetails userDetails = (AuthDetails) authentication.getPrincipal();
            int userNo = userDetails.getUserNo();
            System.out.println("userNo = " + userNo);


            // 누적된 출석 수 가져오기
            Integer attendanceCount = myPageService.getUserAttendanceCount(userNo);
            model.addAttribute("attendanceCount", attendanceCount);

            // 등급 기준 정해주기
            int levelNo = myPageService.calculateLevel(attendanceCount);
            System.out.println("levelNo = " + levelNo);

            // 등급 기준과 출석수 기반으로 등급 업데이트하기
            myPageService.updateUserLevel(userNo, levelNo);

            // 유저의 등급 가져오기
            String levelName = myPageService.getLevelName(levelNo);
            model.addAttribute("userLevel", levelName);

            // 유저의 다음 레벨 이름 가져오기
            String nextLevelName = myPageService.getNextLevelName(levelNo);
            model.addAttribute("nextLevelName", nextLevelName);

            // 다음 등급에 필요한 출석 횟수 조회
            int nextLevelRequiredAttendance = myPageService.getNextLevelRequiredAttendance(levelNo, attendanceCount);
            model.addAttribute("nextLevelRequiredAttendance", nextLevelRequiredAttendance);

            // MyPageDTO에 userNo를 전달하여 사용자 정보를 가져옵니다.
            MyPageDTO myPageDTO = myPageService.findNickName(userNo);
            model.addAttribute("myPageDTO", myPageDTO);

            Map<String, Object> mbtiInfo = myPageService.findUserMBTIInfo(userNo);
            model.addAttribute("mbtiInfo", mbtiInfo);

            MyPageDTO userInfo = myPageService.findUserInfo(userNo);
            model.addAttribute("userInfo", userInfo);

        } else {
            // 인증되지 않은 경우 로그인 페이지로 리다이렉트
            return "redirect:/login";
        }
        return  "main/main";
    }

    @PostMapping("/updateUserLevel")
    @ResponseBody
    public Map<String, Object> updateUserLevel(@AuthenticationPrincipal AuthDetails userDetails) {
        Map<String, Object> response = new HashMap<>();

        if (userDetails != null) {
            int userNo = userDetails.getUserNo();

            // 누적 출석 수 가져오기
            Integer attendanceCount = myPageService.getUserAttendanceCount(userNo);
            response.put("attendanceCount", attendanceCount);

            // 등급 계산
            int levelNo = myPageService.calculateLevel(attendanceCount);

            // 등급 업데이트
            myPageService.updateUserLevel(userNo, levelNo);

            // 등급명 및 다음 등급 정보 가져오기
            String levelName = myPageService.getLevelName(levelNo);
            response.put("levelName", levelName);

            String nextLevelName = myPageService.getNextLevelName(levelNo);
            response.put("nextLevelName", nextLevelName);

            int nextLevelRequiredAttendance = myPageService.getNextLevelRequiredAttendance(levelNo, attendanceCount);
            response.put("nextLevelRequiredAttendance", nextLevelRequiredAttendance);
        }

        return response;
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
            String todayStr = LocalDate.now().toString();

            // 통합된 출석 체크 및 누적 증가 메서드 호출
            return myPageService.checkAndIncrementAttendance(userNo, todayStr);


        }
        return "로그인 정보가 없습니다.";
    }

    // Controller
    @GetMapping("/attendanceStatus")
    @ResponseBody
    public List<String> getAttendanceStatus() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof AuthDetails) {
            AuthDetails userDetails = (AuthDetails) authentication.getPrincipal();
            int userNo = userDetails.getUserNo();

            // 출석 날짜 리스트 반환 (예: ["2024-10-01", "2024-10-02", ...])
            return myPageService.getAttendanceDates(userNo);
        }
        return Collections.emptyList();
    }


//    @GetMapping("/intro")
//    public String intro(){return "/main/intro/intro";}

}
