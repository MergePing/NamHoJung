package com.ohgiraffers.mergyping.post.controller;

import com.ohgiraffers.mergyping.post.model.dto.PostDTO;
import com.ohgiraffers.mergyping.post.model.dto.SelectPostDTO;
import com.ohgiraffers.mergyping.post.model.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Controller
public class PostController {

    private final PostService postService;
    private static final String UPLOAD_DIR = "uploads/";

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/post")
    public String postList(Model model) {
        List<PostDTO> postList = postService.postList();
        model.addAttribute("postList", postList);

        return "/post/post";
    }

    // 게시글 전체조회 페이징 처리
    @GetMapping("/post/page")
    @ResponseBody
    public List<PostDTO> getPosts(@RequestParam("page") int page, @RequestParam("pageSize") int pageSize) {
        List<PostDTO> postList = postService.getPostList();
        int fromIndex = (page - 1) * pageSize;
        int toIndex = Math.min(fromIndex + pageSize, postList.size());
        return postList.subList(fromIndex, toIndex);
    }

    @GetMapping("/post/count")
    @ResponseBody
    public int getPostCount() {
        return postService.getPostCount();
    }




    // 즐겨찾기 토글
    @PostMapping("/toggleFavorite")
    @ResponseBody
    public Map<String, Object> toggleFavorite(@RequestBody Map<String, Object> payload) {
        int postNo = Integer.parseInt((String) payload.get("postNo"));
        boolean isFavorite = (Boolean) payload.get("isFavorite");

        postService.updateFavoriteStatus(postNo, isFavorite);

        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        return response;
    }

    // 게시글 상세조회 페이지
    @GetMapping("/selectpost/{postNo}")
    public String selectById(@PathVariable("postNo") int postNo, Model model) {
        SelectPostDTO selected = postService.selectById(postNo);
        if (selected == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Post not found");
        }
        model.addAttribute("post", selected);
        System.out.println("selected = " + selected);
        return "post/selectpost";
    }

    // 무서워요 토글
    @PostMapping("/toggleScary")
    @ResponseBody
    public Map<String, Object> toggleScary(@RequestBody Map<String, Object> payload) {
        int postNo = Integer.parseInt((String) payload.get("postNo"));
        boolean isScary = (Boolean) payload.get("isScary");

        postService.updateScaryStatus(postNo, isScary);

        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("scaryNumber", postService.getScaryNumber(postNo));
        return response;
    }

    @PostMapping("/toggleNotScary")
    @ResponseBody
    public Map<String, Object> toggleNotScary(@RequestBody Map<String, Object> payload) {
        int postNo = Integer.parseInt((String) payload.get("postNo"));
        boolean isNotScary = (Boolean) payload.get("isNotScary");

        postService.updateNotScaryStatus(postNo, isNotScary);

        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("notScaryNumber", postService.getNotScaryNumber(postNo));
        return response;

    }

    @GetMapping("/newpost")
    public String newPostPage() {
        return "post/newpost";
    }

    @PostMapping("/newpost")
    public ResponseEntity<Map<String, String>> createPost(
            @RequestParam("postTitle") String postTitle,
            @RequestParam("postContent") String postContent,
            @RequestParam("postCategory") String postCategory,
            @RequestParam("postWriter") String postWriter,
            @RequestParam(value = "file", required = false) List<MultipartFile> files) {

        Map<String, String> response = new HashMap<>();
        try {
            SelectPostDTO selectPostDTO = new SelectPostDTO();
            selectPostDTO.setPostTitle(postTitle);
            selectPostDTO.setPostContent(postContent);
            selectPostDTO.setPostCategory(postCategory);
            selectPostDTO.setPostWriter(postWriter);

            // 파일 처리
            if (files != null && !files.isEmpty()) {
                StringBuilder combinedImages = new StringBuilder();
                for (MultipartFile file : files) {
                    Path path = Paths.get(UPLOAD_DIR + file.getOriginalFilename());
                    Files.createDirectories(path.getParent());
                    Files.write(path, file.getBytes());
                    if (combinedImages.length() > 0) {
                        combinedImages.append(",");
                    }
                    combinedImages.append(path.toString());
                }
                selectPostDTO.setPostImage(combinedImages.toString().getBytes(StandardCharsets.UTF_8));
            }

            postService.createPost(selectPostDTO);
            response.put("status", "success");
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace();
            response.put("status", "error");
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PostMapping("/upload")
    @ResponseBody
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
