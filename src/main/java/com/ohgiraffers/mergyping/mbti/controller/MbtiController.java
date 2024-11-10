package com.ohgiraffers.mergyping.mbti.controller;

import com.ohgiraffers.mergyping.auth.model.AuthDetails;
import com.ohgiraffers.mergyping.mbti.model.dto.MbtiResultDTO;
import com.ohgiraffers.mergyping.mbti.model.dto.MbtiTesterDTO;
import com.ohgiraffers.mergyping.mbti.model.dto.QuestionDTO;
import com.ohgiraffers.mergyping.mbti.model.service.MbtiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/mbti")
public class MbtiController {

    @Autowired
    private MbtiService mbtiService;

    @GetMapping()
    public String mbtiStart() {

        return "/mbti/mbtistart";
    }

    @GetMapping("test")
    public String mbtiTest() {
        return "/mbti/mbtitest";
    }

    @GetMapping("/question/{questionNo}")
    @ResponseBody
    public QuestionDTO getQuestion(@PathVariable("questionNo") int questionNo) {
        return mbtiService.getQuestionByNo(questionNo);
    }

    // 결과 페이지 요청
    @GetMapping("/mbtiresult")
    public String resultPage(Model model) {
        // 해당 유저의 MBTI 정보를 DB에서 가져와서 model에 담기
        model.addAttribute("message", "MBTI 결과가 성공적으로 업데이트되었습니다.");
        return "/mbti/mbtiresult";
    }

    @PostMapping("/submitMbtiResult")
    @ResponseBody
    public Map<String, String> submitMbtiResult(@RequestBody MbtiTesterDTO mbtiTesterDTO, Authentication authentication) {
        // 현재 로그인한 사용자의 정보 가져오기
        AuthDetails authDetails = (AuthDetails) authentication.getPrincipal();
        int userNo = authDetails.getUserNo(); // 로그인한 사용자의 userNo를 얻어옵니다.

        // MBTI 계산 및 상태 처리
        String mbtiType = mbtiService.calculateMbti(mbtiTesterDTO);  // MBTI 계산

        // MBTI 결과를 담을 DTO 생성
        MbtiResultDTO mbtiResultDTO = new MbtiResultDTO();
        mbtiResultDTO.setUserNo(userNo);
        mbtiResultDTO.setMbtiType(mbtiType);
        mbtiResultDTO.setMbtiStatus(true);  // MBTI 완료 상태를 true로 설정

        // MBTI 결과 업데이트
        mbtiService.updateMbti(userNo, mbtiResultDTO);

        // 성공적으로 처리 후 결과 페이지로 이동
        Map<String, String> response = new HashMap<>();
        response.put("status", "success");
        return response;
    }
}
