package com.ohgiraffers.mergyping.user.model.dao;

import com.ohgiraffers.mergyping.user.model.dto.UserDTO;
import org.apache.ibatis.annotations.Delete;
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

    // 유저가 작성한 모든 댓글 삭제
    @Delete("DELETE FROM TBL_COMMENT WHERE USER_NO = #{userNo}")
    int deleteCommentsByUserNo(@Param("userNo") int userNo);

    // 유저가 작성한 게시물에 달린 모든 댓글 삭제
    @Delete("DELETE FROM TBL_COMMENT WHERE POST_NO IN (SELECT POST_NO FROM TBL_POST WHERE POST_WRITER = #{userNo})")
    int deleteCommentsByUserPosts(@Param("userNo") int userNo);

    // 액션 삭제
    @Delete("DELETE FROM TBL_ACTION WHERE POST_NO IN (SELECT POST_NO FROM TBL_POST WHERE POST_WRITER = #{userNo})")
    int deleteActionsByUserNo(@Param("userNo") int userNo);

    // 게시물 삭제
    @Delete("DELETE FROM TBL_POST WHERE POST_WRITER = #{userNo}")
    int deletePostsByUserNo(@Param("userNo") int userNo);

    // 유저 삭제
    @Delete("DELETE FROM TBL_USER WHERE USER_NO = #{userNo}")
    int deleteUser(@Param("userNo") int userNo);
}