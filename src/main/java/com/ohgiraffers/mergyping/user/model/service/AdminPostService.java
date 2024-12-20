package com.ohgiraffers.mergyping.user.model.service;

import com.ohgiraffers.mergyping.user.model.dao.AdminPostMapper;
import com.ohgiraffers.mergyping.user.model.dto.AdminPostDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdminPostService {
    private final AdminPostMapper adminPostMapper;

    @Autowired
    public AdminPostService(AdminPostMapper adminPostMapper) {
        this.adminPostMapper = adminPostMapper;
    }

    public Map<String, Object> getPagedPosts(int page, int pageSize) {
        int offset = (page - 1) * pageSize;
        List<AdminPostDTO> posts = adminPostMapper.selectPostsByPage(offset, pageSize);
        int totalPosts = adminPostMapper.countAllPosts();
        int totalPages = (int) Math.ceil((double) totalPosts / pageSize);

        // 페이지네이션 정보 계산
        int startPage = Math.max(1, page - 2);
        int endPage = Math.min(totalPages, page + 2);

        // 결과 반환
        Map<String, Object> pagedPosts = new HashMap<>();
        pagedPosts.put("posts", posts);
        pagedPosts.put("currentPage", page);
        pagedPosts.put("totalPages", totalPages);
        pagedPosts.put("startPage", startPage);
        pagedPosts.put("endPage", endPage);

        return pagedPosts;
    }

    public List<AdminPostDTO> searchPosts(String keyword, int page, int pageSize) {
        int offset = (page - 1) * pageSize;
        return adminPostMapper.searchPosts(keyword, offset, pageSize); // 검색 쿼리 실행
    }

    public int countPostsByKeyword(String keyword) {
        return adminPostMapper.countPostsByKeyword(keyword); // 검색된 게시물 총 개수

    }

    public AdminPostDTO getPostDetail(int postNo) {
        return adminPostMapper.selectPostDetail(postNo);
    }


    public boolean deletePost(int postNo) {
        int rowsAffected = adminPostMapper.deletePost(postNo);
        return rowsAffected > 0; // 삭제 성공 여부 반환
    }
}