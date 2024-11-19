package com.ohgiraffers.mergyping.user.controller;

import com.ohgiraffers.mergyping.user.model.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@Controller
public class AdminMainController {

    private final UserService userService; // final로 선언

    // 생성자 주입
    public AdminMainController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin")
    public String adminMainPage() {
        return "user/admin/admin";
    }

    @PostMapping("/admin/uploadProfile")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> uploadProfile(
            @RequestParam("profilePicture") MultipartFile file,
            @RequestParam("userId") String userId // 사용자 식별자
    ) {
        Map<String, Object> response = new HashMap<>();

        try {
            // 저장 경로 설정
            String uploadDir = "src/main/resources/static/uploads/profile/";
            File uploadDirFile = new File(uploadDir);

            if (!uploadDirFile.exists()) {
                uploadDirFile.mkdirs();
            }

            // 파일 저장
            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Path filePath = Paths.get(uploadDir, fileName);
            Files.write(filePath, file.getBytes());

            // DB에 이미지 경로 저장
            String dbPath = "/uploads/profile/" + fileName;
            userService.updateUserImage(userId, dbPath); // UserService를 통해 업데이트

            // 응답
            response.put("success", true);
            response.put("filePath", dbPath);
            return ResponseEntity.ok(response);

        } catch (IOException e) {
            response.put("success", false);
            response.put("message", "파일 업로드 실패");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
