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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Controller
public class PostController {

    private final PostService postService;
    private static final String UPLOAD_DIR = "src/main/resources/static/uploads/";
    private static final List<String> ALLOWED_EXTENSIONS = Arrays.asList("jpg", "jpeg", "pn0g","gif");
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

    @GetMapping("/post/page")
    @ResponseBody
    public List<PostDTO> getPosts(@RequestParam("page") int page, @RequestParam("pageSize") int pageSize) {
        List<PostDTO> postList = postService.getPostList();
        int fromIndex = (page - 1) * pageSize;
        int toIndex = Math.min(fromIndex + pageSize, postList.size());
        return postList.subList(fromIndex, toIndex);
    }

    @GetMapping("/selectpost/{postNo}")
    public String selectById(@PathVariable("postNo") int postNo, Model model) {
        SelectPostDTO selected = postService.selectById(postNo);
        if (selected == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Post not found");
        }

        // 이미지 경로 설정
        if (selected.getPostImageFirst() != null) {
            selected.setPostImageFirst("/uploads/" + selected.getPostImageFirst());
        }
        if (selected.getPostImageSecond() != null) {
            selected.setPostImageSecond("/uploads/" + selected.getPostImageSecond());
        }

        model.addAttribute("post", selected);
        return "post/selectpost"; // 이 부분이 HTML 파일과 매핑되는지 확인
    }



    @GetMapping("/post/count")
    @ResponseBody
    public int getPostCount() {
        return postService.getPostCount();
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
    public String newPostPage(Model model) {
        model.addAttribute("post", new SelectPostDTO());
        return "post/newpost";
    }

    // 첫 번째 이미지 업로드
    @PostMapping("/uploadFirstImage")
    @ResponseBody
    public Map<String, String> uploadFirstImage(@RequestParam("file") MultipartFile file,int postNo) {
        return handleFileUpload(file, postNo,1);
    }

    // 두 번째 이미지 업로드
    @PostMapping("/uploadSecondImage")
    @ResponseBody
    public Map<String, String> uploadSecondImage(@RequestParam("file") MultipartFile file ,int postNo) {
        return handleFileUpload(file, postNo,2);
    }

    private Map<String, String> handleFileUpload(MultipartFile file,int postNo, int imageNo) {
        Map<String, String> response = new HashMap<>();
        if (file.isEmpty()) {
            response.put("error", "파일이 선택되지 않았습니다.");
            return response;
        }

        String fileExtension = getFileExtension(file.getOriginalFilename());
        if (!ALLOWED_EXTENSIONS.contains(fileExtension.toLowerCase())){
            response.put("error","이미지 파일만 업로드 가능합니다");
            return response;
        }

        try {
            String filePath = saveFile(file,postNo,imageNo);
            response.put("filPath",filePath);
            return response;
        } catch (IOException e){
            e.printStackTrace();
            response.put("error", "파일 업로드 실패: " + e.getMessage());
            return response;
        }
        }


    @PostMapping("/newpost")
    public ResponseEntity<Map<String, String>> createPost(
            @RequestParam("postTitle") String postTitle,
            @RequestParam("postContent") String postContent,
            @RequestParam("postCategory") String postCategory,
            @RequestParam("postWriter") String postWriter,
            @RequestParam("postNo") int postNo,
            @RequestParam(value = "fileFirst", required = false) MultipartFile fileFirst,
            @RequestParam(value = "fileSecond", required = false) MultipartFile fileSecond) {

        Map<String, String> response = new HashMap<>();
        try {
            SelectPostDTO selectPostDTO = new SelectPostDTO();
            selectPostDTO.setPostTitle(postTitle);
            selectPostDTO.setPostContent(postContent);
            selectPostDTO.setPostCategory(postCategory);
            selectPostDTO.setPostWriter(postWriter);
            selectPostDTO.setPostNo(postNo);

            // 첫 번째 이미지 파일 처리
            if (fileFirst != null && !fileFirst.isEmpty()) {
                String firstImagePath = saveFile(fileFirst, postNo,1);
                selectPostDTO.setPostImageFirst(firstImagePath);
            }

            // 두 번째 이미지 파일 처리
            if (fileSecond != null && !fileSecond.isEmpty()) {
                String secondImagePath = saveFile(fileSecond, postNo,2);
                selectPostDTO.setPostImageSecond(secondImagePath);
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
    private String saveFile(MultipartFile file, int postNo, int imageNo) throws IOException {
        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String fileName = date + "/" + postNo + "/" + imageNo + "." + getFileExtension(file.getOriginalFilename());
        Path path = Paths.get(UPLOAD_DIR + fileName);
        Files.createDirectories(path.getParent());
        Files.write(path, file.getBytes());
        return "/uploads/" + fileName;
    }


    private String getFileExtension(String fileName) {
        int lastIndexOfDot = fileName.lastIndexOf('.');
        if (lastIndexOfDot == -1) {
            return "";
        }
        return fileName.substring(lastIndexOfDot + 1); }

}

