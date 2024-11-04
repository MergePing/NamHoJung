package com.ohgiraffers.mergyping.post.controller;

import com.ohgiraffers.mergyping.post.model.dto.PostDTO;
import com.ohgiraffers.mergyping.post.model.dto.SelectPostDTO;
import com.ohgiraffers.mergyping.post.model.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService){
        this.postService=postService;
    }

    @GetMapping("/post")
    public String postList(Model model){
        List<PostDTO> postList = postService.postList();
        model.addAttribute("postList",postList);

        return "/post/post";
    }



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

    @GetMapping("/posts")
    @ResponseBody
    public List<PostDTO> getPosts(@RequestParam("page") int page, @RequestParam("pageSize") int pageSize) {
        return postService.getPostsByPage(page, pageSize);
    }

    @GetMapping("/selectpost/{postNo}")
    public String selectPost(@PathVariable("postNo") int postNo, Model model) {
        SelectPostDTO selected = postService.selectById(postNo);
        if (selected == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Post not found");
        }
        model.addAttribute("selected", selected);
        return "post/selectpost";
    }





}
