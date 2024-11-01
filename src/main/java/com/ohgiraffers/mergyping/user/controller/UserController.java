package com.ohgiraffers.mergyping.user.controller;

import com.ohgiraffers.mergyping.user.model.dto.UserDTO;
import com.ohgiraffers.mergyping.user.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 유저 리스트를 조회하여 페이지네이션과 함께 출력
    @GetMapping("/admin/users")
    public String showUserList(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "8") int pageSize,
            Model model) {

        List<UserDTO> users = userService.getUsersByPage(page, pageSize);
        int totalUsers = userService.countUsers();
        int totalPages = (int) Math.ceil((double) totalUsers / pageSize);

        int startPage = Math.max(1, page - 2);
        int endPage = Math.min(totalPages, page + 2);

        model.addAttribute("userList", users);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "/user/admin/adminmanagement";
    }

    // 선택한 회원의 상세 정보를 JSON 형식으로 반환
    @GetMapping("/admin/user/{userId}")
    @ResponseBody
    public UserDTO getUserDetails(@PathVariable("userId") int userId) {
        return userService.getUserById(userId);
    }
}