package com.ohgiraffers.mergyping.user.controller;

import com.ohgiraffers.mergyping.user.model.dto.AdminPostDTO;
import com.ohgiraffers.mergyping.user.model.service.AdminPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

    // HTML 렌더링용 메서드
    @GetMapping("/admin/post")
    public String adminPosts(Model model,
                             @RequestParam(defaultValue = "1") int page,
                             @RequestParam(defaultValue = "7") int pageSize) {
        Map<String, Object> pagedPosts = adminPostService.getPagedPosts(page, pageSize);

        // 모델에 페이지 정보 추가
        model.addAttribute("postList", pagedPosts.get("posts"));
        model.addAttribute("currentPage", pagedPosts.get("currentPage"));
        model.addAttribute("totalPages", pagedPosts.get("totalPages"));
        model.addAttribute("startPage", pagedPosts.get("startPage"));
        model.addAttribute("endPage", pagedPosts.get("endPage"));

        return "user/admin/adminpost"; // View 반환
    }

    // AJAX 데이터 반환용 메서드
    @GetMapping("/admin/post/data")
    @ResponseBody
    public Map<String, Object> getPosts(@RequestParam(defaultValue = "1") int page,
                                        @RequestParam(defaultValue = "7") int pageSize) {
        Map<String, Object> pagedPosts = adminPostService.getPagedPosts(page, pageSize);

        // JSON 응답으로 페이지 정보 반환
        Map<String, Object> response = new HashMap<>();
        response.put("postList", pagedPosts.get("posts"));
        response.put("currentPage", pagedPosts.get("currentPage"));
        response.put("totalPages", pagedPosts.get("totalPages"));

        return response;
    }


    @GetMapping("/admin/post/search")
    @ResponseBody
    public Map<String, Object> searchPosts(@RequestParam("keyword") String keyword,
                                           @RequestParam(defaultValue = "1") int page,
                                           @RequestParam(defaultValue = "7") int pageSize) {
        List<AdminPostDTO> posts = adminPostService.searchPosts(keyword, page, pageSize);
        int totalPosts = adminPostService.countPostsByKeyword(keyword); // 검색된 게시물 수
        int totalPages = (int) Math.ceil((double) totalPosts / pageSize);

        Map<String, Object> response = new HashMap<>();
        response.put("postList", posts);
        response.put("currentPage", page);
        response.put("totalPages", totalPages);

        return response;
    }
}