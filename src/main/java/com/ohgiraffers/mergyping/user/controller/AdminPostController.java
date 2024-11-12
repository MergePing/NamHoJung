package com.ohgiraffers.mergyping.user.controller;

import com.ohgiraffers.mergyping.user.model.dto.AdminPostDTO;
import com.ohgiraffers.mergyping.user.model.service.AdminPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AdminPostController {

    private final AdminPostService adminPostService;

    @Autowired
    public AdminPostController(AdminPostService adminPostService) {
        this.adminPostService = adminPostService;
    }

    // 게시물 리스트 페이지
    @GetMapping("/admin/post")
    public String adminPosts(Model model,
                             @RequestParam(defaultValue = "1") int page,
                             @RequestParam(defaultValue = "7") int pageSize) {

        // 서비스 호출
        List<AdminPostDTO> posts = adminPostService.getPosts(page, pageSize);
        int totalPosts = adminPostService.getTotalPosts();
        int totalPages = (int) Math.ceil((double) totalPosts / pageSize);

        // 모델에 데이터 추가
        model.addAttribute("postList", posts);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("startPage", Math.max(1, page - 2));
        model.addAttribute("endPage", Math.min(totalPages, page + 2));

        return "user/admin/adminpost"; // 뷰 반환
    }
}