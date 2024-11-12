package com.ohgiraffers.mergyping.user.model.dao;

import com.ohgiraffers.mergyping.user.model.dto.MyPageDTO;
import com.ohgiraffers.mergyping.user.model.dto.MyPagePostDTO;
import com.ohgiraffers.mergyping.user.model.dto.MypageCommentDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Mapper
public interface MyPageMapper {
    MyPageDTO findNickName(int userNo);

    MyPageDTO findEmail(int userNo);

    boolean existsByNickname(String nickname);

    void modifyUserName(MyPageDTO myPageDTO);

    List<MyPagePostDTO> findWrittenPost(int userNo);

    void updatePassword(Map<String,Object> params);


    MyPageDTO findId(int userNo);

    List<MypageCommentDTO> findWrittenComment(int userNo);

    List<MyPagePostDTO> findWrittenFavorite(int userNo);

    Map<String, Object> findUserMBTIInfo(int userNo);


    boolean hasCheckedToday(@Param("userNo") int userNo, @Param("today") String todayStr);

    void checkAttendance(@Param("userNo") int userNo, @Param("today") String todayStr);

    void incrementAttendanceCount(@Param("userNo") int userNo);

    List<String> getAttendanceDates(int userNo);

    Integer getUserAttendanceCount(int userNo);

    void updateUserLevel(Map<String, Object> params);

    String getLevelName(int levelNo);

    String getNextLevelName(int currentLevelNo);


    void deleteUser(int userNo);

    void updateProfileImage(@RequestParam("userNo") int userNo, @RequestParam("fileUrl") String fileUrl);

    MyPageDTO findUserInfo(@RequestParam("userNo") int userNo);

}
