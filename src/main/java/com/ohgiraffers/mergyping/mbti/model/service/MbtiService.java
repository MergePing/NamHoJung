package com.ohgiraffers.mergyping.mbti.model.service;

import com.ohgiraffers.mergyping.mbti.model.dao.MbtiMapper;
import com.ohgiraffers.mergyping.mbti.model.dto.MbtiInfoDTO;
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

        if (mbtiTesterDTO.getCB() >= 1) {
            mbtiType += "C";
        } else if (mbtiTesterDTO.getCB() <= -1) {
            mbtiType += "B";
        }
        if (mbtiTesterDTO.getHG() >= 1) {
            mbtiType += "H";
        } else if (mbtiTesterDTO.getHG() <= -1) {
            mbtiType += "G";
        }
        if (mbtiTesterDTO.getSE() >= 1) {
            mbtiType += "S";
        } else if (mbtiTesterDTO.getSE() <= -1) {
            mbtiType += "E";
        }
        if (mbtiTesterDTO.getTM() >= 1) {
            mbtiType += "T";
        } else if (mbtiTesterDTO.getTM() <= -1) {
            mbtiType += "M";
        }

        return mbtiType;
    }

    public void updateMbti(int userNo, MbtiResultDTO mbtiResultDTO) {
        mbtiMapper.updateMbti(userNo, mbtiResultDTO);
    }
    
    // 여기는 처음에 로그인한 유저번호를 authDetails에서 가져와서 사용할때 쓰던 공간 -> 첫번째 쿼리문에 사용했음
    public MbtiResultDTO getMbtiResult(int userNo) {
        return mbtiMapper.findMbtiResultByUserNo(userNo);
    }

    // 가져온 유저넘버를 통해 type으로 가져오는 공간 -> 두번째 쿼리문에 사용할것
    public MbtiInfoDTO findByType(String mbtiType) {
        return mbtiMapper.findMbtiInfoByType(mbtiType);
    }
}
