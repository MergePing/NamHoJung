package com.ohgiraffers.mergyping.post.controller;

import com.ohgiraffers.mergyping.post.model.dto.FileUploadDTO;
import com.ohgiraffers.mergyping.post.model.dto.PostDTO;
import com.ohgiraffers.mergyping.post.model.dto.SelectPostDTO;
import com.ohgiraffers.mergyping.post.model.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;
import java.nio.file.Files;
import java.nio.file.Paths;


@Controller
public class PostController {

    private final PostService postService;

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



}