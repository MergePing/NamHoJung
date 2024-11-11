package com.ohgiraffers.mergyping.mbti.model.dao;

import com.ohgiraffers.mergyping.mbti.model.dto.MbtiResultDTO;
import com.ohgiraffers.mergyping.mbti.model.dto.QuestionDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MbtiMapper {
    QuestionDTO getQuestion(int questionNo);

    void updateMbti(int userNo, MbtiResultDTO mbtiResultDTO);
}
