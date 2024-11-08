package com.ohgiraffers.mergyping.user.model.service;

import com.ohgiraffers.mergyping.user.model.dao.MyPageMapper;
import com.ohgiraffers.mergyping.user.model.dto.MyPageDTO;
import com.ohgiraffers.mergyping.user.model.dto.MyPagePostDTO;
import com.ohgiraffers.mergyping.user.model.dto.MypageCommentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MyPageService {

    private final MyPageMapper myPageMapper;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    public MyPageService(MyPageMapper myPageMapper, PasswordEncoder passwordEncoder) {
        this.myPageMapper = myPageMapper;
        this.passwordEncoder = passwordEncoder;

    }

    public MyPageDTO findNickName(int userNo) {
        return myPageMapper.findNickName(userNo);
    }

    public MyPageDTO findEmail(int userNo) {
        return myPageMapper.findEmail(userNo);
    }


    public boolean isNicknameDuplicate(String nickname) {
        return myPageMapper.existsByNickname(nickname);
    }

    @Transactional
    public void modifyUserName(MyPageDTO myPageDTO) {
        myPageMapper.modifyUserName(myPageDTO);
    }

    public List<MyPagePostDTO> findWrittenPost(int userNo) {

        return myPageMapper.findWrittenPost(userNo);
    }

    @Transactional
    public void updatePassword(Map<String,Object> params) {
        myPageMapper.updatePassword(params);
    }

    public MyPageDTO findId(int userNo) {
        return myPageMapper.findId(userNo);
    }

    public List<MypageCommentDTO> findWrittenComment(int userNo) {
        return myPageMapper.findWrittenComment(userNo);
    }

    public List<MyPagePostDTO> findWrittenFavorite(int userNo) {
        return myPageMapper.findWrittenFavorite(userNo);
    }

    public Map<String, Object> findUserMBTIInfo(int userNo) {
        return myPageMapper.findUserMBTIInfo(userNo);
    }

    @Transactional
    public String checkAndIncrementAttendance(int userNo, String todayStr) {
        // 출석 체크 여부 확인
        if (myPageMapper.hasCheckedToday(userNo, todayStr)) {
            return "이미 출석을 체크하셨습니다.";
        }

        // 출석 체크 등록
        myPageMapper.checkAttendance(userNo, todayStr);

        // 누적 출석 수 증가
        System.out.println("누적 출석 수 증가 쿼리 실행: " + userNo);
        myPageMapper.incrementAttendanceCount(userNo);

        return "출석 체크가 완료되었습니다.";

    }
}

