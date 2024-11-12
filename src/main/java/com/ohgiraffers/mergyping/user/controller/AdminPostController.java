package com.ohgiraffers.mergyping.user.controller;

import com.ohgiraffers.mergyping.user.model.dto.AdminPostDTO;
import com.ohgiraffers.mergyping.user.service.AdminPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AdminPostController {

    private final AdminPostService adminPostService;

    @Autowired
    public AdminPostController(AdminPostService adminPostService) {
        this.adminPostService = adminPostService;
    }

    // 초기 로딩 시 게시물 리스트 페이지를 보여줍니다 (URL 변경 없음)
    @GetMapping("/admin/post")
    public String showPostList(Model model) {
        int page = 1; // 초기 로딩 시 기본 페이지 번호
        int pageSize = 7;

        List<AdminPostDTO> posts = adminPostService.getPostsByPage(page, pageSize);
        int totalPosts = adminPostService.countAllPosts();
        int totalPages = (int) Math.ceil((double) totalPosts / pageSize);

        model.addAttribute("postList", posts);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("startPage", Math.max(1, page - 2));
        model.addAttribute("endPage", Math.min(totalPages, page + 2));

        return "/user/admin/adminpost"; // 게시판 페이지 뷰 로드
    }

    // AJAX 요청으로 게시물 리스트를 JSON 형식으로 반환
    @GetMapping("/admin/post/data")
    @ResponseBody
    public Map<String, Object> getPostListData(@RequestParam(defaultValue = "1") int page,
                                               @RequestParam(defaultValue = "7") int pageSize) {
        List<AdminPostDTO> posts = adminPostService.getPostsByPage(page, pageSize);
        int totalPosts = adminPostService.countAllPosts();
        int totalPages = (int) Math.ceil((double) totalPosts / pageSize);

        Map<String, Object> response = new HashMap<>();
        response.put("postList", posts);
        response.put("currentPage", page);
        response.put("totalPages", totalPages);
        response.put("startPage", Math.max(1, page - 2));
        response.put("endPage", Math.min(totalPages, page + 2));

        return response; // 페이지네이션 데이터 JSON 반환
    }
}