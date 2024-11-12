package com.ohgiraffers.mergyping.user.model.dao;

import com.ohgiraffers.mergyping.user.model.dto.AdminPostDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminPostMapper {
    // 페이지네이션을 위한 게시물 목록 조회
    List<AdminPostDTO> selectPostsByPage(int offset, int pageSize);

    // 전체 게시물 수 조회
    int countAllPosts();
}