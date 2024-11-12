package com.ohgiraffers.mergyping.post.controller;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

@Controller
public class StaticResourceController {

    private final ResourceLoader resourceLoader;

    public StaticResourceController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @GetMapping("/uploads/{date}/{postNo}/{fileName}")
    public ResponseEntity<Resource> getFile(
            @PathVariable String date,
            @PathVariable String postNo,
            @PathVariable String fileName) {

        // 요청된 파일 경로에 따라 리소스를 로드
        Resource resource = resourceLoader.getResource("file:src/main/resources/static/uploads/" + date + "/" + postNo + "/" + fileName);

        // 로드 된 리소스 응답 반환
        return ResponseEntity.ok(resource);
    }


}