package com.ohgiraffers.mergyping.mbti.model.dao;

import com.ohgiraffers.mergyping.mbti.model.dto.MbtiInfoDTO;
import com.ohgiraffers.mergyping.mbti.model.dto.MbtiResultDTO;
import com.ohgiraffers.mergyping.mbti.model.dto.QuestionDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MbtiMapper {
    QuestionDTO getQuestion(int questionNo);

    void updateMbti(int userNo, MbtiResultDTO mbtiResultDTO);

    // 두개 세트라고 보면 됨 -> 다른 DB 엔터티끼리 연결하기 위한 부분
    MbtiResultDTO findMbtiResultByUserNo(int userNo);
    MbtiInfoDTO findMbtiInfoByType(String mbtiType);
}
