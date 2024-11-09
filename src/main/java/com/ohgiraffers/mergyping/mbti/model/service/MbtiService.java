package com.ohgiraffers.mergyping.mbti.model.service;

import com.ohgiraffers.mergyping.mbti.model.dao.MbtiMapper;
import com.ohgiraffers.mergyping.mbti.model.dto.MbtiResultDTO;
import com.ohgiraffers.mergyping.mbti.model.dto.MbtiTesterDTO;
import com.ohgiraffers.mergyping.mbti.model.dto.QuestionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MbtiService {

    private final MbtiMapper mbtiMapper;

    @Autowired
    public MbtiService(MbtiMapper mbtiMapper) {
        this.mbtiMapper = mbtiMapper;
    }

    public QuestionDTO getQuestionByNo(int questionNo) {
            return mbtiMapper.getQuestion(questionNo);
    }

    // MBTI 유형 계산
    public String calculateMbti(MbtiTesterDTO mbtiTesterDTO) {
        // MBTI 유형을 결정할 변수
        String mbtiType = "";

        // 각 점수에 따라 MBTI 유형을 계산
        mbtiType += mbtiTesterDTO.getCB() >= 0 ? "C" : "E";
        mbtiType += mbtiTesterDTO.getHG() >= 0 ? "H" : "S";
        mbtiType += mbtiTesterDTO.getSE() >= 0 ? "S" : "N";
        mbtiType += mbtiTesterDTO.getTM() >= 0 ? "T" : "F";

        return mbtiType;
    }

    // MBTI 유형을 DB에 업데이트
    public void updateMbti(int userNo, MbtiResultDTO mbtiResultDTO) {
        // mbtiResultDTO 객체를 사용하여 DB에 업데이트
        mbtiMapper.updateMbti(userNo, mbtiResultDTO);
    }
}
