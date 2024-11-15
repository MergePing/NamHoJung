package com.ohgiraffers.mergyping.post.controller;

import com.ohgiraffers.mergyping.auth.model.AuthDetails;
import com.ohgiraffers.mergyping.comment.model.dto.CommentDTO;
import com.ohgiraffers.mergyping.post.model.dto.InsertPostDTO;
import com.ohgiraffers.mergyping.post.model.dto.PostDTO;
import com.ohgiraffers.mergyping.post.model.dto.SelectPostDTO;
import com.ohgiraffers.mergyping.post.model.dto.WriterNameDTO;
import com.ohgiraffers.mergyping.post.model.service.PostService;
import com.ohgiraffers.mergyping.user.model.dto.MyPageDTO;
import com.ohgiraffers.mergyping.user.model.service.MyPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Controller
public class PostController {

    // 필드 정의
    // 서비스 주입받는 필드
    private final PostService postService;
    private final MyPageService myPageService;

    // 이미지가 저장될 경로
    private static final String UPLOAD_DIR = "src/main/resources/static/uploads/";

    // 이미지 파일만 받기 위한 허용되는 확장자 목록
    private static final List<String> ALLOWED_EXTENSIONS = Arrays.asList("jpg", "jpeg", "png","gif","webp");


    // 서비스 생성자
    @Autowired
    public PostController(PostService postService,MyPageService myPageService) {
        this.postService = postService;
        this.myPageService=myPageService;
    }


    //게시글 목록 반환
    @GetMapping("/post")
    public String postList(Model model) {

        // 서비스를 통해 PostDTO에 있는 게시글 목록 가져옴
        List<PostDTO> postList = postService.postList();

        // 모델에 postList라는 이름으로 게시글 목롣 추가
        model.addAttribute("postList", postList);

        //뷰 반환
        return "/post/post";
    }

    // 게시글 삭제 요청 처리
    @DeleteMapping("/post/{postNo}/delete")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> deletePost(@PathVariable int postNo, Authentication authentication) {
        // 로그인된 유저와 게시글 작성자가 일치하는지 확인
        AuthDetails userDetails = (AuthDetails) authentication.getPrincipal();
        int userNo = userDetails.getUserNo();

        SelectPostDTO post = postService.selectPostWriter(postNo);
        System.out.println("post.getPostWriter() = " + post.getPostWriter());
        System.out.println("post = " + post);
        System.out.println("userNo = " + userNo);

        Map<String, Object> response = new HashMap<>();
        if (post != null && post.getPostWriter() == userNo) {

            // 게시글 삭제 수행
            boolean isDeleted = postService.deletePost(postNo);
            if (isDeleted) {
                response.put("success", true);
                response.put("message", "게시글이 삭제되었습니다.");
                return ResponseEntity.ok(response);
            } else {
                response.put("success", false);
                response.put("error", "게시글 삭제에 실패했습니다.");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
            }
        } else {
            response.put("success", false);
            response.put("error", "삭제 권한이 없습니다.");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
        }
    }



    // 게시글 정렬
    @GetMapping("/post/sort")
    public ResponseEntity<List<PostDTO>> postListSort(
            @RequestParam(required = false) String orderBy,
            @RequestParam(required = false) String category,
            @RequestParam("page") int page,
            @RequestParam("pageSize") int pagSize
    ) {

        // 서비스를 통해 PostDTO에 있는 게시글 목록 가져옴
        List<PostDTO> posts = postService.postListSort(orderBy,category,page,pagSize);

        //뷰 반환
        return ResponseEntity.ok(posts);
    }


    @GetMapping("/post/page")
    public ResponseEntity<List<PostDTO>> getPostsByPage(
            @RequestParam("page") int page, @RequestParam("pageSize") int pageSize) {

//        // 서비스에서 게시글 목록 받아옴
//        List<PostDTO> postList = postService.getPostList();
//
//        // 페이징 처리를 위해 시작 인덱스 지정
//        // 페이지는 1부터 시작하지만 인덱스는 0부터 시작하기 때문에 -1을 추가
//        int fromIndex = (page - 1) * pageSize;
//        // 리스트의 크기를 넘지 않도록 시작인덱스에 페이지의 크기를 더한 것과 게시글 목록의 크기중 작은 값을 종료 인덱스에 저장
//        int toIndex = Math.min(fromIndex + pageSize, postList.size());
//
//        // 지정된 범위의 게시글 목록 반환 시작인덱스 부터 종료 인덱스까지의 리스트 반환
//        return postList.subList(fromIndex, toIndex);
        List<PostDTO> posts = postService.getPostsByPage(page, pageSize);
        return ResponseEntity.ok(posts);
    }

    public ResponseEntity<List<PostDTO>> postListSort1(
            @RequestParam(required = false) String orderBy,
            @RequestParam(required = false) String category,
            @RequestParam("page") int page,
            @RequestParam("pageSize") int pageSize) {
        List<PostDTO> posts = postService.postListSort1(orderBy, category, page, pageSize);
        return ResponseEntity.ok(posts);
    }



    @GetMapping("/selectpost/{postNo}")
    public String selectById(@PathVariable("postNo") int postNo, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        int userNo = -1;
        if (authentication != null && authentication.getPrincipal() instanceof AuthDetails) {
            AuthDetails userDetails = (AuthDetails) authentication.getPrincipal();
            userNo = userDetails.getUserNo();

        }

        // 서비스를 통해 게시글 번호로 게시물 조회
        SelectPostDTO selected = postService.selectById(postNo);

        // 게시물을 찾지 못한 경우 404 에러 반환
        if (selected == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Post not found");
        }

        // 이미지의 null 여부 확인 후 이미지 경로 설정
        if (selected.getPostImageFirst() != null) {
            selected.setPostImageFirst(selected.getPostImageFirst());
        }

        if (selected.getPostImageSecond() != null) {
            selected.setPostImageSecond(selected.getPostImageSecond());
        }

        // 모델에 post라는 이름으로 선택한 게시글 추가
        model.addAttribute("post", selected);

        // 댓글 목록 및 사용자 정보 추가
        List<CommentDTO> comments = postService.getCommentsByPostNo(postNo);
        model.addAttribute("comments", comments);
        model.addAttribute("userNo", userNo);
        MyPageDTO userInfo = myPageService.findUserInfo(userNo);
        model.addAttribute("userInfo", userInfo);




        // 프로필 이미지 추가
        String profileImage = null;
        if (selected.getPostWriter() == userNo) {
            profileImage = myPageService.getProfileImageByUserNo(userNo);
        }
        model.addAttribute("profileImage", profileImage);

        // 작성자의 등급 및 이름 설정
        MyPageDTO level = myPageService.findUserInfo(userNo);
        model.addAttribute("level", level);

        MyPageDTO userName = myPageService.findUserInfo(userNo);
        model.addAttribute("userInfo", userName);


        return "post/selectpost";
    }


    @PostMapping("/selectpost/{postNo}/comment")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> addComment(@PathVariable("postNo") int postNo,
                                                          @RequestParam("commentContent") String commentContent)
    {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !(authentication.getPrincipal() instanceof AuthDetails)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "로그인 후 댓글을 작성할 수 있습니다."));
        }

        AuthDetails userDetails = (AuthDetails) authentication.getPrincipal();
        int userNo = userDetails.getUserNo();

        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setCommentContent(commentContent);
        commentDTO.setUserNo(userNo);
        commentDTO.setPostNo(postNo);

        List<CommentDTO> existingComments = postService.getCommentsByPostNo(postNo);
        int index = existingComments.size();

        commentDTO.setTop(index * 150);



        // 댓글 추가 후 응답 설정
        boolean isAdded = postService.addComment(commentDTO);
        System.out.println("isAdded = " + isAdded);
        Map<String, Object> response = new HashMap<>();

        if (isAdded) {
            // 댓글 추가 성공 후, 게시물의 댓글 수를 증가시킴
            boolean isCommentCountUpdated = postService.incrementCommentCount(postNo);

            if (isCommentCountUpdated) {
                // 댓글 수 증가에 성공한 경우 최신 댓글 목록을 가져옴
                List<CommentDTO> comments = postService.getCommentsByPostNo(postNo);
                // 댓글에 index 값을 추가
                for (int i = 0; i < comments.size(); i++) {
                    comments.get(i).setIndex(i); // index 값 추가
                }
                response.put("success", true);
                response.put("comments", comments); // 생성된 댓글 정보 반환
            } else {
                response.put("success", false);
                response.put("error", "댓글 수 업데이트에 실패했습니다.");
            }
        } else {
            // 댓글 작성 실패 시
            response.put("success", false);
            response.put("error", "댓글 작성에 실패했습니다.");
        }

        return ResponseEntity.ok(response);

    }

    @DeleteMapping("/selectpost/{postNo}/comment/{commentNo}/delete")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> deleteComment(@PathVariable int postNo, @PathVariable int commentNo) {
        boolean isDeleted = postService.deleteComment(commentNo);

        Map<String, Object> response = new HashMap<>();
        if (isDeleted) {
            postService.decreaseCommentCount(postNo);
            response.put("success", true);
            response.put("message", "댓글이 삭제되었습니다.");

            // 최신 댓글 목록을 가져와서 포함
            List<CommentDTO> comments = postService.getCommentsByPostNo(postNo);
            response.put("comments", comments);
            return ResponseEntity.ok(response);
        } else {
            response.put("success", false);
            response.put("error", "댓글 삭제에 실패했습니다.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @PutMapping("/selectpost/{postNo}/comment/{commentNo}/edit")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> editComment(
            @PathVariable("postNo") int postNo,
            @PathVariable("commentNo") int commentNo,
            @RequestParam("commentContent") String commentContent,
            @AuthenticationPrincipal AuthDetails authDetails) {

        Map<String, Object> response = new HashMap<>();
        int userNo = authDetails.getUserNo();

        boolean success = postService.updateComment(commentNo, commentContent, userNo);
        if (success) {
            List<CommentDTO> comments = postService.getCommentsByPostNo(postNo);
            response.put("success", true);
            response.put("comments", comments);
        } else {
            response.put("success", false);
            response.put("error", "댓글 수정에 실패했습니다.");
        }
        return ResponseEntity.ok(response);

    }

    @GetMapping("/post/count")
    @ResponseBody
    public int getPostCount() {

        // 서비스를 통해 게물의 개수 반환
        return postService.getPostCount();
    }


    @PostMapping("/toggleFavorite")
    @ResponseBody
    public Map<String, Object> toggleFavorite(@RequestBody Map<String, Object> payload) {
        // payload - 클라이언트가 서버로 요청을 보낼 때 요청의 본문에 포함된 데이터를 말함
        // @RequestBody - 요청된 본문을 자바 형식으로 변경

        // 요청 본문에서 게시물 번호를 가져와서 정수형으로 형변환
        int postNo = Integer.parseInt((String) payload.get("postNo"));

        // 요청 본문에서 즐겨찾기 여부를 불리언 값으로 가져옴
        boolean isFavorite = (Boolean) payload.get("isFavorite");

        // 서비스를 통해 게시물 번호와 즐겨찾기 여부로 즐겨 찾기 상태 업데이트
        postService.updateFavoriteStatus(postNo, isFavorite);

        // 응답을 저장할 새로운 맵 생성
        Map<String, Object> response = new HashMap<>();

        // 응답 맵에 성공 상태 추가
        response.put("success", true);

        // 응답 맵 반환
        return response;
    }



    // 무서워요 토글
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


        @PostMapping("/toggleLike")
    @ResponseBody
    public Map<String, Object> toggleLike(@RequestBody Map<String, Object> payload) {
        Object commentNoObj = payload.get("commentNo");
        int commentNo;

        if (commentNoObj instanceof Integer) {
            commentNo = (Integer) commentNoObj;
            System.out.println("commentNo 111111111= " + commentNo);
        } else if (commentNoObj instanceof String) {
            commentNo = Integer.parseInt((String) commentNoObj);
            System.out.println("commentN22222222222o = " + commentNo);
        } else {
            throw new IllegalArgumentException("Invalid type for commentNo");
        }

        boolean isLike = (Boolean) payload.get("isLike");
        postService.updateLikeStatus(commentNo, isLike);

        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("likeNumber", postService.getLikeNumber(commentNo));
        return response;
    }









    // 게시물 수정 페이지 이동
    @GetMapping("/edit/{postNo}")
    public String editPostPage(@PathVariable("postNo") int postNo, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        int userNo = -1;
        if (authentication != null && authentication.getPrincipal() instanceof AuthDetails) {
            AuthDetails userDetails = (AuthDetails) authentication.getPrincipal();
            userNo = userDetails.getUserNo();
        }

        // 서비스를 통해 게시글 번호로 게시물 조회
        SelectPostDTO selected = postService.selectById(postNo);

        // 게시물을 찾지 못한 경우 404 에러 반환
        if (selected == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Post not found");
        }

        // 이미지의 null 여부 확인 후 이미지 경로 설정
        if (selected.getPostImageFirst() != null) {
            selected.setPostImageFirst(selected.getPostImageFirst());
        }

        if (selected.getPostImageSecond() != null) {
            selected.setPostImageSecond(selected.getPostImageSecond());
        }

        // 모델에 post라는 이름으로 선택한 게시글 추가
        model.addAttribute("post", selected);



        // 댓글 목록 및 사용자 정보 추가
        List<CommentDTO> comments = postService.getCommentsByPostNo(postNo);
        MyPageDTO userInfo = myPageService.findUserInfo(userNo);
        model.addAttribute("userInfo", userInfo);
        model.addAttribute("userInfo", userInfo);
        model.addAttribute("comments", comments);
        model.addAttribute("userNo", userNo);


        // 프로필 이미지 추가
        String profileImage = null;
        if (selected.getPostWriter() == userNo) {
            profileImage = myPageService.getProfileImageByUserNo(userNo);
        }
        model.addAttribute("profileImage", profileImage);

        // 작성자의 등급 및 이름 설정
        MyPageDTO level = myPageService.findUserInfo(userNo);
        model.addAttribute("level", level);

        MyPageDTO userName = myPageService.findUserInfo(userNo);
        model.addAttribute("userInfo", userName);

        return "post/edit";

    }


    // 게시물 수정 처리
    @PostMapping("/edit")
    public ResponseEntity<Map<String, String>> editPost(
            @RequestParam("postTitle") String postTitle,
            @RequestParam("postContent") String postContent,
            @RequestParam("postCategory") String postCategory,
            @RequestParam("postWriter") int postWriter,
            @RequestParam("postNo") int postNo,
            @RequestParam(value = "fileFirst", required = false) MultipartFile fileFirst,
            @RequestParam(value = "fileSecond", required = false) MultipartFile fileSecond) {

        Map<String, String> response = new HashMap<>();
        try {
            InsertPostDTO insertPostDTO = new InsertPostDTO();
            insertPostDTO.setPostTitle(postTitle);
            insertPostDTO.setPostContent(postContent);
            insertPostDTO.setPostCategory(postCategory);
            insertPostDTO.setPostWriter(postWriter);
            insertPostDTO.setPostNo(postNo);

            if (fileFirst != null && !fileFirst.isEmpty()) {
                String firstImagePath = saveFile(fileFirst, postNo, 1);
                insertPostDTO.setPostImageFirst("/uploads/" + firstImagePath);
            }

            if (fileSecond != null && !fileSecond.isEmpty()) {
                String secondImagePath = saveFile(fileSecond, postNo, 2);
                insertPostDTO.setPostImageSecond("/uploads/" + secondImagePath);
            }

            postService.updatePost(insertPostDTO);
            response.put("status", "success");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            response.put("status", "error");
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }



    @GetMapping("/newpost")
    public String newPostPage(Model model,WriterNameDTO writer) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof AuthDetails) {
            AuthDetails userDetails = (AuthDetails) authentication.getPrincipal();
            int userNo = userDetails.getUserNo();
            System.out.println("userNo = " + userNo);

            MyPageDTO userInfo = myPageService.findUserInfo(userNo);
            model.addAttribute("userInfo", userInfo);

//            MyPageDTO writerNo = myPageService.findUserInfo(userNo);
//            model.addAttribute("writerNo", writerNo);

            SelectPostDTO newPost = new SelectPostDTO();
            newPost.setPostNo(generateNewPostNo());
            model.addAttribute("post", newPost);


        } else {
            // 인증되지 않은 경우 로그인 페이지로 리다이렉트
            return "redirect:/login";
        }
        return "post/newpost";
    }



    // 새 게시물 번호를 생성하는 메서드 예시
    private int generateNewPostNo() {

        // 현재 데이터베이스에서 최대 postNo를 가져와서 +1을 해서 새로운 게시물 번호 생성
        int maxPostNo = postService.getMaxPostNo();

        // 새로 생긴 게시물 번호 반환
        return maxPostNo + 1;
    }


    //파일 업로드를 처리하는 핸들러 메소드
    private Map<String, String> handleFileUpload(MultipartFile file,int postNo, int imageNo) {

        // 응답할 데이터를 저장할 새로운 맵 생성
        Map<String, String> response = new HashMap<>();

        //파일이 비어있는지 왁인 후 비어있다면 에러 메세지 출력
        if (file.isEmpty()) {
            response.put("error", "파일이 선택되지 않았습니다.");

            return response;
        }

        // 파일 확장자를 가져옴
        // 만약 파일이 img1.PNG라면 PNG 반환
        String fileExtension = getFileExtension(file.getOriginalFilename());

        // 허용된 확장자를 가져와서 소문자로 바꿈 PNG -> png
        // 만약 허용되어 있지 않은 확장자라면 에러 메세지 출력
        if (!ALLOWED_EXTENSIONS.contains(fileExtension.toLowerCase())){
            response.put("error","이미지 파일만 업로드 가능합니다");

            return response;
        }

        try {

            //파일 저장 및 경로 저장
            String filePath = saveFile(file,postNo,imageNo);

            // 파일경로를 응답 맵에 추가및 출렫
            response.put("filPath",filePath);

            return response;
        }

        catch (IOException e){

            // 예외 발생 시 에러 메세지를 응답에 추가 및 출력
            e.printStackTrace();
            response.put("error", "파일 업로드 실패: " + e.getMessage());

            return response;
        }
    }

    // 첫 번째 이미지 업로드
    @PostMapping("/uploadFirstImage")
    @ResponseBody
    public Map<String, String> uploadFirstImage(@RequestParam("file") MultipartFile file,int postNo) {

        // 파일이라는 매개변수를 받아서 첫번째 이미지를 업로드 하는 핸들러메소드 호출
        return handleFileUpload(file, postNo,1);
    }

    // 두 번째 이미지 업로드
    @PostMapping("/uploadSecondImage")
    @ResponseBody
    public Map<String, String> uploadSecondImage(@RequestParam("file") MultipartFile file ,int postNo) {

        // 파일이라는 매개변수를 받아서 두번째 이미지를 업로드 하는 핸들러메소드 호출
        return handleFileUpload(file, postNo,2);
    }



    //새 게시물 생성
    @PostMapping("/newpost")
    public ResponseEntity<Map<String, String>> createPost(
            // @RequestParam으로 요청된 매개변수의 이름과 타입 지정
            @RequestParam("postTitle") String postTitle,
            @RequestParam("postContent") String postContent,
            @RequestParam("postCategory") String postCategory,
            @RequestParam("postWriter") int postWriter,
            @RequestParam("postNo") int postNo,

            // required = flase로 매개변수가 필수가 아님을 표시
            // MultipartFile fileFirst 는 클라이언트가 업로드한 파일을 나타냅
            @RequestParam(value = "fileFirst", required = false) MultipartFile fileFirst,
            @RequestParam(value = "fileSecond", required = false) MultipartFile fileSecond) {


        //응답을 받을 맵 생성
        Map<String, String> response = new HashMap<>();
        try {
            // 새로 생성된 게시물의 데이터들을 저장할 새로운 selectPostDTO 생성, setter로 값 주입
            InsertPostDTO insertPostDTO = new InsertPostDTO();
            insertPostDTO.setPostTitle(postTitle);
            insertPostDTO.setPostContent(postContent);
            insertPostDTO.setPostCategory(postCategory);
            insertPostDTO.setPostWriter(postWriter);
            insertPostDTO.setPostNo(postNo);

            // 첫번째 파일이 null이 아니고 비어있지 않은 경우 파일, 게시글 번호, 이미지번호를 저장
            if (fileFirst != null && !fileFirst.isEmpty()) {
                String firstImagePath = saveFile(fileFirst, postNo, 1);

                // 첫번째 이미지의 확장자 주입
                String firstFileExtension = getFileExtension(fileFirst.getOriginalFilename());
                insertPostDTO.setPostImageFirstExtension(firstFileExtension);
                System.out.println("firstFileExtension = " + firstFileExtension);

                // 첫번째 파일에 클라이언트가 접근할수있는 경로 주입
                insertPostDTO.setPostImageFirst("/uploads/"+firstImagePath);
            }else {
                System.out.println("fileFirst is null or empty");
            }

            // 두번째 파일이 null이 아니고 비어있지 않은 경우 파일, 게시글 번호, 이미지번호를 저장
            if (fileSecond != null && !fileSecond.isEmpty()) {
                String secondImagePath = saveFile(fileSecond, postNo, 2);

                String secondFileExtension = getFileExtension(fileSecond.getOriginalFilename());
                insertPostDTO.setPostImageSecondExtension(secondFileExtension);
                System.out.println("secondFileExtension = " + secondFileExtension);

                // 두번째 파일에 클라이언트가 접근할수있는 경로 주입
                insertPostDTO.setPostImageSecond("/uploads/"+secondImagePath);
            }else {
                System.out.println("fileSecond is null or empty");
            }

            //게시물을 생성하는서비스 메소드 호출
            postService.createPost(insertPostDTO);

            //--여기는 잘 모르겠음--
            // 대충 아까 만들어둔 응답 맵에 성공적인 상태라고 저장하는거 같음
            response.put("status", "success");

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace();

            // 에러가 발생할 경우 응답맵에 에러 저장
            response.put("status", "error");
            response.put("message", e.getMessage());

            // 에러 발생시 500에러 페이지 반환
            // HttpStatus.INTERNAL_SERVER_ERROR = 500에러
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }



    //파일의 이름을 날짜/게시글 번호/이미지 번호로 바꾸고 업로드 폴더에 리턴
    private String saveFile(MultipartFile file, int postNo, int imageNo) throws IOException {
        // 오늘 날짜를 yyyy-mm-dd 형식으로 포맷
        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        // 파일 확장자 추출 아래 getFileExtension 메소드 참고
        //file.getOriginalFilename()는 img1.png 반환, getFileExtension(img1.png)는 png 반환
        String fileExtension = getFileExtension(file.getOriginalFilename());
        System.out.println("111111111111111"+fileExtension);

        // 파일 이름을 위에서 설정한 날짜, 게시글 번호, 이미지 번호. 추출한 확장자로 바꿈 ex) 2024-11-08_13_1.jpg
        String fileName = date + "_" + postNo + "_" + imageNo + "." + fileExtension;

        // 이미지 경로를 날짜/filename 경로로 설정 ex) 2024-11-08/2024-11-08_13_1.jpg
        // 이미지 경로 - 클라이언트에게 제공할때 불러오는 경로
        // !!!! 이미지를 클라이언트에게 제공하려면, HTML에서 이미지의 src 속성에 이미지 경로를 사용해야함 !!!!!!
        // 아마 이게 오류 였던듯 게속 서버상 경로를 링크에 걸었음
        String imagePath = date + "/" + fileName;

        //이미지 서버 경로 설정 ex) src/main/resources/static/uploads/2024-11-08/2024-11-08_13_1.jpg
        // 서버경로 - 서버에 있는 내 파일에 저장되는 경로
        Path path = Paths.get(UPLOAD_DIR +imagePath);

        // 파일을 저장할 디렉토리 생성, 이미 있는경우 생성하지 않음
        // 이미지 1번을 저장할때는 2024-11-08/ 디렉토리 생성, 2번은 그냥 그 하위로 들어감
        Files.createDirectories(path.getParent());

        // 지정한 path에 바이트 배열의 파일을 생성하고 파일을 저장, 만약 파일이 이미 존재하면 덮어씀
        Files.write(path, file.getBytes());

        System.out.println("Original Filename: " + file.getOriginalFilename());


        // 디버깅 확인을 위한 파일 경로 출력
        System.out.println("File saved at: " + path.toString());

        // 클라이언트가 이미지를 볼수있게 이미지 경로 반환

        System.out.println("222222222222"+imagePath);
        return imagePath;
    }



    // 파일 확장자 추출 .이 있으면 .뒤에 오는 확장자(png,jpg)를 추출하고 없는 경우 빈 문자열 반환
    private String getFileExtension(String fileName) {

        // 파일에서 마지막.의 인덱스를 찾음
        // 인덱스에 .이 없는 경우 -1 을 반환함(인데스는 0번부터 시적하기 때문에 -1 반환) -1일 경우 빈 문자열 반환
        if (fileName==null||fileName.lastIndexOf('.') == -1) {
            return "";
        }

        // substring은 시작하는 인덱스부터 마지막 인덱스까지를 반환하는 메소드
        // 따라서 .이후를 반환하려면 1을 더해줘야함
        // 파일이름의 .이후를 확장자로 반환
        return fileName.substring(fileName.lastIndexOf('.') + 1).toLowerCase();
    }

    // 게시물 검색

    @GetMapping("/searchpost")
    @ResponseBody
    public Map<String, Object> searchPosts(@RequestParam("keyword") String keyword) {
        Map<String, Object> response = new HashMap<>();
        try {
            // 키워드가 입력되지 않앗을 때
            if (keyword == null || keyword.trim().isEmpty()) {
                throw new IllegalArgumentException("키워드를 입력해주세요.");
            }

            // 키워드 UTF-8로 디코딩
            // 여기까진 문제 없음
            String decodedKeyword = URLDecoder.decode(keyword, StandardCharsets.UTF_8);
            System.out.println("디코딩된 검색어: " + decodedKeyword);

            // 키워드를 통해 검색 수행
            // 문제 발생 null
            // 해결 완료 xml쪽 문제였음
            List<PostDTO> posts = postService.searchPost(keyword);
            System.out.println("검색 결과: " + posts);

            // 검색 결과를 맵에 담기
            response.put("posts", posts);
            System.out.println("response = " + response);
        }

        catch (IllegalArgumentException e) {
            // 키워드가 없을 경우 에러 처리
            response.put("error", e.getMessage());
            return response;
        } catch (Exception e) {
            // 기타 에러 처리
            e.printStackTrace();
            response.put("error", "서버 오류가 발생했습니다.");
            return response;
        }
        return response; // JSON 형식으로 응답을 반환
    }



        @GetMapping("/delete/{postNo}")
        public String getDeletePostPage(@PathVariable("postNo") int postNo, Model model) {
            model.addAttribute("postNo", postNo);
            return "deletePost"; // deletePost.html 페이지를 반환합니다.
        }

        @PostMapping("/delete/{postNo}")
        public String deletePost(@PathVariable("postNo") int postNo, RedirectAttributes redirectAttributes) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null && authentication.getPrincipal() instanceof AuthDetails) {
                AuthDetails userDetails = (AuthDetails) authentication.getPrincipal();
                int userNo = userDetails.getUserNo();

                SelectPostDTO selected = postService.selectById(postNo);

                if (selected != null && selected.getPostWriter() == userNo) {
                    postService.deletePost(postNo);
                    redirectAttributes.addFlashAttribute("message", "게시글이 삭제되었습니다.");
                    return "redirect:/post";
                }
            }
            redirectAttributes.addFlashAttribute("errorMessage", "삭제 권한이 없습니다.");
            return "redirect:/post";
        }
    }










