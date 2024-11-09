package com.ohgiraffers.mergyping.mbti.model.service;

import com.ohgiraffers.mergyping.mbti.model.dao.MbtiMapper;
import com.ohgiraffers.mergyping.mbti.model.dto.QuestionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MbtiService {

    @Autowired
    private MbtiMapper mbtiMapper;

    public QuestionDTO getQuestionByNo(int questionNo) {
            return mbtiMapper.getQuestion(questionNo);
    }
}
