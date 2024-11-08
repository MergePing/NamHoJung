package com.ohgiraffers.mergyping.post.controller;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
        Resource resource = resourceLoader.getResource("file:src/main/resources/static/uploads/" + date + "/" + postNo + "/" + fileName);
        return ResponseEntity.ok(resource);
    }
}
