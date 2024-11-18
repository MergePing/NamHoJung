package com.ohgiraffers.mergyping.mbti.controller;

import com.ohgiraffers.mergyping.auth.model.AuthDetails;
import com.ohgiraffers.mergyping.mbti.model.dto.MbtiInfoDTO;
import com.ohgiraffers.mergyping.mbti.model.dto.MbtiResultDTO;
import com.ohgiraffers.mergyping.mbti.model.dto.MbtiTesterDTO;
import com.ohgiraffers.mergyping.mbti.model.dto.QuestionDTO;
import com.ohgiraffers.mergyping.mbti.model.service.MbtiService;
import com.ohgiraffers.mergyping.user.model.dto.MyPageDTO;
import com.ohgiraffers.mergyping.user.model.service.MyPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/mbti")
public class MbtiController {

    private final MyPageService myPageService;

    // 서비스 생성자
    @Autowired
    public MbtiController(MyPageService myPageService) {
        this.myPageService = myPageService;
    }

    @Autowired
    private MbtiService mbtiService;



    @GetMapping()
    public String mbtiStart(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof AuthDetails) {
            AuthDetails userDetails = (AuthDetails) authentication.getPrincipal();
            int userNo = userDetails.getUserNo();

            MyPageDTO userInfo = myPageService.findUserInfo(userNo);
            model.addAttribute("userInfo", userInfo);


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
        }

        return "/mbti/mbtistart";
    }

    @GetMapping("/test")
    public String mbtiTest(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof AuthDetails) {
            AuthDetails userDetails = (AuthDetails) authentication.getPrincipal();
            int userNo = userDetails.getUserNo();

            MyPageDTO userInfo = myPageService.findUserInfo(userNo);
            model.addAttribute("userInfo", userInfo);


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
        }

        // 처음 안내문구를 위해 생성
        QuestionDTO question = new QuestionDTO();
        question.setText("질문에 대한 답변을 솔직하게 해주세요 😂");
        model.addAttribute("QuestionDTO", question);

        return "/mbti/mbtitest";
    }

    @GetMapping("/question/{questionNo}")
    @ResponseBody
    public QuestionDTO getQuestion(@PathVariable("questionNo") int questionNo) {
        return mbtiService.getQuestionByNo(questionNo);
    }

    @GetMapping("/mbtiresult")
    public String showMbtiResult(Model model, Authentication authentication) {
        AuthDetails authDetails = (AuthDetails) authentication.getPrincipal();
        int userNo = authDetails.getUserNo();

        MyPageDTO userInfo = myPageService.findUserInfo(userNo);
        model.addAttribute("userInfo", userInfo);


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




        Map<String, Object> mbtiInfo2 = myPageService.findUserMBTIInfo(userNo);
        model.addAttribute("mbtiInfo", mbtiInfo2);

        MbtiResultDTO mbtiResult = mbtiService.getMbtiResult(userNo);

        MbtiInfoDTO mbtiInfo = mbtiService.findByType(mbtiResult.getMbtiType());

        model.addAttribute("mbtiResultDTO", mbtiResult);
        model.addAttribute("mbtiInfoDTO", mbtiInfo);

        return "/mbti/mbtiresult";
    }

    @PostMapping("/submitmbtiresult")
    @ResponseBody
    public Map<String, String> submitMbtiResult(@RequestBody MbtiTesterDTO mbtiTesterDTO, Authentication authentication) {
        AuthDetails authDetails = (AuthDetails) authentication.getPrincipal();
        int userNo = authDetails.getUserNo();

        String mbtiType = mbtiService.calculateMbti(mbtiTesterDTO);  // MBTI 계산

        MbtiResultDTO mbtiResultDTO = new MbtiResultDTO();
        mbtiResultDTO.setUserNo(userNo);
        mbtiResultDTO.setMbtiType(mbtiType);
        mbtiResultDTO.setMbtiStatus(true);

        mbtiService.updateMbti(userNo, mbtiResultDTO);

        Map<String, String> response = new HashMap<>();
        response.put("status", "success");
        return response;
    }
}
