package com.ohgiraffers.mergyping.user.controller;

import com.ohgiraffers.mergyping.user.model.dto.UserDTO;
import com.ohgiraffers.mergyping.user.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin/users")
    public String showUserList(
            @RequestParam(defaultValue = "1") int page, // 요청 페이지 번호 (기본값 1)
            @RequestParam(defaultValue = "8") int pageSize, // 페이지당 유저 수 (기본값 8)
            Model model) {

        // 요청한 페이지에 해당하는 유저 목록 가져오기
        List<UserDTO> users = userService.getUsersByPage(page, pageSize);

        // 전체 유저 수로 페이지 수 계산
        int totalUsers = userService.countUsers(); // 유저 총 수를 가져오는 서비스 메서드 필요
        int totalPages = (int) Math.ceil((double) totalUsers / pageSize);

        // 페이지네이션 범위 설정
        int startPage = Math.max(1, page - 2);
        int endPage = Math.min(totalPages, page + 2);

        // 모델에 페이지네이션 정보 추가
        model.addAttribute("userList", users);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "/user/admin/adminmanagement";
    }
}