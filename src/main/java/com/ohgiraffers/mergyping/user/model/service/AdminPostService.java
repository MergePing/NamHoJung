package com.ohgiraffers.mergyping.user.model.service;

import com.ohgiraffers.mergyping.user.model.dao.AdminPostMapper;
import com.ohgiraffers.mergyping.user.model.dto.AdminPostDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminPostService {
    private final AdminPostMapper adminPostMapper;

    @Autowired
    public AdminPostService(AdminPostMapper adminPostMapper) {
        this.adminPostMapper = adminPostMapper;
    }

    // 페이지 번호와 페이지 크기를 기반으로 게시물 목록을 가져오는 메서드
    public List<AdminPostDTO> getPosts(int page, int pageSize) {
        int offset = (page - 1) * pageSize; // 오프셋 계산
        return adminPostMapper.selectPostsByPage(offset, pageSize);
    }

    // 전체 게시물 수를 가져오는 메서드
    public int getTotalPosts() {
        return adminPostMapper.countAllPosts();
    }
}