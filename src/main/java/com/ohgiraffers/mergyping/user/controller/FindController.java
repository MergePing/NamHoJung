package com.ohgiraffers.mergyping.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RequestMapping("/find")
@Controller
public class FindController {

    @Autowired
    private FindService findService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/select")
    public String search() {

        return "/user/searchuser/searchuser";
    }

    @GetMapping("/id")
    public String searchId() {

        return "/user/searchuser/searchId";
    }

    @GetMapping("/findid")
    public ResponseEntity<Map<String, Object>> findId(@RequestParam String userEmail) {
        Map<String, Object> response = new HashMap<>();
        Optional<FindUserDTO> findUserDTO = findService.findId(userEmail);
        if (findUserDTO.isPresent()) {
            response.put("success", true);
            response.put("id", findUserDTO.get().getId());
            return ResponseEntity.ok(response);
        } else {
            response.put("success", false);
            response.put("message", "아이디를 찾을 수 없습니다.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @GetMapping("/password")
    public String searchPwd() {

        return "/user/searchuser/searchPwd";
    }

    @PostMapping("/changepwd")
    public ResponseEntity<?> changePassword(@RequestBody FindUserDTO findUserDTO) {
        String email = findUserDTO.getEmail();
        String newPassword = findUserDTO.getNewPassword();

        System.out.println("Email: " + email);
        System.out.println("New Password: " + newPassword);

        boolean isPasswordChanged = findService.changePassword(email, newPassword);

        if (isPasswordChanged) {
            return ResponseEntity.ok().body("{\"success\": true, \"message\": \"비밀번호가 성공적으로 변경되었습니다.\"}");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"success\": false, \"message\": \"비밀번호 변경에 실패했습니다.\"}");
        }
    }
}
