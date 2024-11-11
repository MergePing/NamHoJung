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

    public List<String> getAttendanceDates(int userNo) {
        return myPageMapper.getAttendanceDates(userNo);
    }

    //누적된 출석 수 가져오기
    public Integer getUserAttendanceCount(int userNo) {
        return myPageMapper.getUserAttendanceCount(userNo);
    }

    // 등급 정해주기
    public int calculateLevel(int attendanceCount) {
        if (attendanceCount >= 161) {
            return 10; // 고인물 정원사
        } else if (attendanceCount >= 141) {
            return 9;  // 최고의 정원사
        } else if (attendanceCount >= 121) {
            return 8;  // 역사에 남을 정원사
        } else if (attendanceCount >= 101) {
            return 7;  // 베테랑 정원사
        } else if (attendanceCount >= 81) {
            return 6;  // 성공한 정원사
        } else if (attendanceCount >= 61) {
            return 5;  // 최상급 정원사
        } else if (attendanceCount >= 41) {
            return 4;  // 고급 정원사
        } else if (attendanceCount >= 21) {
            return 3;  // 중급 정원사
        } else if (attendanceCount >= 11) {
            return 2;  // 초보 정원사
        } else {
            return 1;  // 신입 정원사
        }

    }

    // 등급 업데이트 해주기
    public void updateUserLevel(int userNo, int levelNo) {
        Map<String, Object> params = new HashMap<>();
        params.put("userNo", userNo);
        params.put("levelNo", levelNo);
        myPageMapper.updateUserLevel(params);

    }

    // 유저의 등급 조회하기
    public String getLevelName(int levelNo) {
        return myPageMapper.getLevelName(levelNo);
    }

    // 유저 다음 등급 조회하기
    public String getNextLevelName(int currentLevelNo) {
        return myPageMapper.getNextLevelName(currentLevelNo);
    }

    // 유저의 다음등급까지 남은 출석 수 조회
    public int getNextLevelRequiredAttendance(int levelNo, int userAttendanceCount) {
        // 각 레벨의 최소 출석 횟수 기준
        int[] levelAttendanceThresholds = {0, 11, 21, 41, 61, 81, 101, 121, 141, 161};

        // currentLevelNo는 1부터 시작하므로, 배열 인덱스는 그대로 사용
        if (levelNo < 1 || levelNo >= levelAttendanceThresholds.length) {
            return 0; // 유효하지 않은 레벨 번호일 경우
        }

        // 다음 레벨의 최소 출석 횟수
        int nextLevelAttendance = levelAttendanceThresholds[levelNo];

        // 현재 출석 횟수와 비교하여, 부족한 출석 횟수 계산
        if (userAttendanceCount >= nextLevelAttendance) {
            return 0; // 이미 해당 레벨을 넘었거나, 다음 레벨로 갈 수 있음
        } else {
            return nextLevelAttendance - userAttendanceCount; // 부족한 출석 횟수
        }
    }

    public void deleteUserAccount(int userNo) {
        myPageMapper.deleteUser(userNo);
    }
}

