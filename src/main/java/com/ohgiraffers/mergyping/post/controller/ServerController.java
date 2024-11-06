package com.ohgiraffers.mergyping.post.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

@RestController
public class ServerController {

        private static final String UPLOAD_DIR = "uploads/";

        @PostMapping("/upload")
        public Map<String, String> uploadFile(@RequestParam("file") MultipartFile file) {
            Map<String, String> response = new HashMap<>();
            if (file.isEmpty()) {
                response.put("error", "파일이 선택되지 않았습니다.");
                return response;
            }

            try {
                // 파일 저장 경로 설정
                Path path = Paths.get(UPLOAD_DIR + file.getOriginalFilename());
                Files.createDirectories(path.getParent());
                Files.write(path, file.getBytes());

                response.put("filePath", path.toString());
                return response;
            } catch (IOException e) {
                e.printStackTrace();
                response.put("error", "파일 업로드 실패: " + e.getMessage());
                return response;
            }
        }
    }

