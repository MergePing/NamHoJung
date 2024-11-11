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

    public String calculateMbti(MbtiTesterDTO mbtiTesterDTO) {
        String mbtiType = "";

        mbtiType += mbtiTesterDTO.getCB() >= 1 ? "C" : "B";
        mbtiType += mbtiTesterDTO.getHG() >= 1 ? "H" : "G";
        mbtiType += mbtiTesterDTO.getSE() >= 1 ? "S" : "E";
        mbtiType += mbtiTesterDTO.getTM() >= 1 ? "T" : "M";

        return mbtiType;
    }

    public void updateMbti(int userNo, MbtiResultDTO mbtiResultDTO) {
        mbtiMapper.updateMbti(userNo, mbtiResultDTO);
    }
}
