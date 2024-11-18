package com.ohgiraffers.mergyping.user.controller;

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

    @GetMapping("/admin")
    public String adminMainPage() {

        return "user/admin/admin";
    }



    @PostMapping("/admin/uploadProfile")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> uploadProfile(@RequestParam("profilePicture") MultipartFile file) {
        Map<String, Object> response = new HashMap<>();

        try {
            // resources/static/uploads/profile 경로 설정
            String uploadDir = new File("src/main/resources/static/uploads/profile").getAbsolutePath();
            File uploadDirFile = new File(uploadDir);


            // 파일 저장
            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Path filePath = Paths.get(uploadDir, fileName);
            Files.write(filePath, file.getBytes());

            // 반환할 URL 경로 (정적 리소스 핸들링)
            response.put("success", true);
            response.put("filePath", "/uploads/profile/" + fileName);
            return ResponseEntity.ok(response);
        } catch (IOException e) {
            response.put("success", false);
            response.put("message", "파일 업로드 실패");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
