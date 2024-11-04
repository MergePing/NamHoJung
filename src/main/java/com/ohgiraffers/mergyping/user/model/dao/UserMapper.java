package com.ohgiraffers.mergyping.user.model.dao;

import com.ohgiraffers.mergyping.user.model.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    List<UserDTO> selectAllUsers();

    // 페이지별 유저 목록 조회
    List<UserDTO> selectUsersByPage(@Param("offset") int offset, @Param("pageSize") int pageSize);

    // 전체 유저 수 조회
    int countAllUsers();

    UserDTO selectUserById(int userId);

    int updateNickname(@Param("userNo") String userNo, @Param("nickname") String nickname);

    void updateUserDeleteStatus(String userNo, boolean isDeleted, String deleteDate);

    // 닉네임 검색 결과 목록 조회
    List<UserDTO> searchUsersByNickname(@Param("nickname") String nickname);

}