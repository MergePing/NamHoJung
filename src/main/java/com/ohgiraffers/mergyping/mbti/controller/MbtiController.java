package com.ohgiraffers.mergyping.mbti.controller;

import com.ohgiraffers.mergyping.auth.model.AuthDetails;
import com.ohgiraffers.mergyping.mbti.model.dto.MbtiInfoDTO;
import com.ohgiraffers.mergyping.mbti.model.dto.MbtiResultDTO;
import com.ohgiraffers.mergyping.mbti.model.dto.MbtiTesterDTO;
import com.ohgiraffers.mergyping.mbti.model.dto.QuestionDTO;
import com.ohgiraffers.mergyping.mbti.model.service.MbtiService;
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

    @GetMapping("/mbtiresult")
    public String showMbtiResult(Model model, Authentication authentication) {
        AuthDetails authDetails = (AuthDetails) authentication.getPrincipal();
        int userNo = authDetails.getUserNo();

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
