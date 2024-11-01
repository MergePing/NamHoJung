package com.ohgiraffers.mergyping.user.controller;

import com.ohgiraffers.mergyping.user.model.dto.UserDTO;
import com.ohgiraffers.mergyping.user.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 초기 로딩 시 사용자 리스트 페이지를 보여줍니다 (URL 변경 없음)
    @GetMapping("/admin/users")
    public String showUserList(Model model) {
        int page = 1; // 초기 로딩 시 기본 페이지 번호
        int pageSize = 8;

        List<UserDTO> users = userService.getUsersByPage(page, pageSize);
        int totalUsers = userService.countUsers();
        int totalPages = (int) Math.ceil((double) totalUsers / pageSize);

        model.addAttribute("userList", users);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("startPage", Math.max(1, page - 2));
        model.addAttribute("endPage", Math.min(totalPages, page + 2));

        return "/user/admin/adminmanagement"; // 초기 페이지 뷰 로드
    }

    // AJAX 요청으로 사용자 리스트를 JSON 형식으로 반환
    @GetMapping("/admin/users/data")
    @ResponseBody
    public Map<String, Object> getUserListData(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "8") int pageSize) {
        List<UserDTO> users = userService.getUsersByPage(page, pageSize);
        int totalUsers = userService.countUsers();
        int totalPages = (int) Math.ceil((double) totalUsers / pageSize);

        Map<String, Object> response = new HashMap<>();
        response.put("userList", users);
        response.put("currentPage", page);
        response.put("totalPages", totalPages);
        response.put("startPage", Math.max(1, page - 2));
        response.put("endPage", Math.min(totalPages, page + 2));

        return response; // 페이지네이션 데이터 JSON 반환
    }

    // 선택한 회원의 상세 정보를 JSON으로 반환
    @GetMapping("/admin/user/{userId}")
    @ResponseBody
    public UserDTO getUserDetails(@PathVariable("userId") int userId) {
        return userService.getUserById(userId);
    }

    @DeleteMapping("/admin/user/delete/{userNo}")
    public ResponseEntity<Void> deleteUser(@PathVariable("userNo") int userNo) {
        boolean isDeleted = userService.deleteUserByNo(userNo);

        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}