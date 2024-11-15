package com.ohgiraffers.mergyping.user.controller;

import com.ohgiraffers.mergyping.auth.model.AuthDetails;
import com.ohgiraffers.mergyping.common.UserRole;
import com.ohgiraffers.mergyping.user.model.dto.MyPageDTO;
import com.ohgiraffers.mergyping.user.model.dto.MyPagePostDTO;
import com.ohgiraffers.mergyping.user.model.dto.MypageCommentDTO;
import com.ohgiraffers.mergyping.user.model.service.MyPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MyPageController {

    private final MyPageService myPageService;
    private final PasswordEncoder passwordEncoder;
    private static final String UPLOAD_DIR = "src/main/resources/static/uploads/";


    @Autowired
    public MyPageController(MyPageService myPageService, PasswordEncoder passwordEncoder) {
        this.myPageService = myPageService;
        this.passwordEncoder = passwordEncoder;

    }


        @ModelAttribute("userInfo")
        public MyPageDTO addUserInfoToModel() {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            if (authentication != null && authentication.getPrincipal() instanceof AuthDetails) {
                AuthDetails userDetails = (AuthDetails) authentication.getPrincipal();
                int userNo = userDetails.getUserNo();
                return myPageService.findUserInfo(userNo);
            }
            return new MyPageDTO();  // 인증되지 않은 경우 빈 DTO 반환
        }



    @GetMapping("/userinfo")
    public String findUserInfo(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof AuthDetails) {
            AuthDetails userDetails = (AuthDetails) authentication.getPrincipal();
            int userNo = userDetails.getUserNo();
            UserRole role = userDetails.getUserRole();

            // MyPageDTO에 userNo를 전달하여 사용자 정보를 가져옵니다.
            MyPageDTO myPageDTO = myPageService.findNickName(userNo);
            model.addAttribute("myPageDTO", myPageDTO);

            MyPageDTO myPageDTO2 = myPageService.findEmail(userNo);
            model.addAttribute("myPageDTO2", myPageDTO2);

            MyPageDTO myPageDTO3 = myPageService.findId(userNo);
            model.addAttribute("myPageDTO3", myPageDTO3);

            MyPageDTO userInfo = myPageService.findUserInfo(userNo);

            model.addAttribute("userInfo", userInfo);

            MyPageDTO level = myPageService.findUserInfo(userNo);
            model.addAttribute("level", level);

            MyPageDTO userName = myPageService.findUserInfo(userNo);
            model.addAttribute("userName", userName);

            MyPageDTO writerNo = myPageService.findUserInfo(userNo);
            model.addAttribute("writerNo", writerNo);



        } else {
            // 인증되지 않은 경우 로그인 페이지로 리다이렉트
            return "redirect:/login";
        }

        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals("ADMIN"));

        if (isAdmin) {
            return "redirect:/admin";
        } else {
            return "user/mypage/userinfo"; // userinfo.html로 이동
        }
    }

    @PostMapping("/userinfo")
    public String modifyUserName(@ModelAttribute MyPageDTO myPageDTO,@RequestParam(required = false) String newPassword) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof AuthDetails) {
            AuthDetails userDetails = (AuthDetails) authentication.getPrincipal();
            int userNo = userDetails.getLoginUserDTO().getUserNo();  // 로그인한 사용자의 userNo 가져오기

            System.out.println("userNo = " + userNo);

            // MyPageDTO에 로그인된 사용자 userNo 설정
            myPageDTO.setUserNo(userNo);
            // 비밀번호가 전달되지 않더라도 닉네임 수정은 항상 진행
            if (myPageDTO.getUserName() != null && !myPageDTO.getUserName().isEmpty()) {
                myPageService.modifyUserName(myPageDTO);  // 닉네임 수정
            }

            // 비밀번호가 전달된 경우 비밀번호 업데이트
            if (newPassword != null && !newPassword.isEmpty()) {
                System.out.println("Before encryption: " + newPassword);
                String userPwd = passwordEncoder.encode(newPassword);
                System.out.println("Encoded password: " + userPwd);// 비밀번호 암호화
                // Map을 사용하여 userNo와 userPwd를 전달
                Map<String, Object> params = new HashMap<>();
                params.put("userNo", userNo);
                params.put("userPwd", userPwd);

                myPageService.updatePassword(params);  // 암호화된 비밀번호로 업데이트

                // 비밀번호 변경 후 세션의 Authentication 정보를 갱신 (새 비밀번호로 로그인)
                if (authentication != null && authentication.getPrincipal() instanceof AuthDetails) {
                    userDetails = (AuthDetails) authentication.getPrincipal();
                    userDetails.getLoginUserDTO().setUserPass(userPwd); // 새 비밀번호로 설정
                    Authentication newAuthentication = new UsernamePasswordAuthenticationToken(userDetails, userPwd, authentication.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(newAuthentication);
                }
            }
        } else {
            return "redirect:/login";  // 인증되지 않은 경우 로그인 페이지로 리다이렉트
        }

        return "redirect:/userinfo";
    }



    @GetMapping("/useractive")
    public String findNickName(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof AuthDetails) {
            AuthDetails userDetails = (AuthDetails) authentication.getPrincipal();
            int userNo = userDetails.getUserNo();
            System.out.println("userNo = " + userNo);

            // MyPageDTO에 userNo를 전달하여 사용자 정보를 가져옵니다.
            MyPageDTO myPageDTO = myPageService.findNickName(userNo);
            model.addAttribute("myPageDTO", myPageDTO);


            MyPageDTO userInfo = myPageService.findUserInfo(userNo);
            model.addAttribute("userInfo", userInfo);

            Map<String, Object> mbtiInfo = myPageService.findUserMBTIInfo(userNo);
            model.addAttribute("mbtiInfo", mbtiInfo);

        } else {
            // 인증되지 않은 경우 로그인 페이지로 리다이렉트
            return "redirect:/login";
        }

        return "user/mypage/useractive";
    }


    @GetMapping("/getPosts")
    public ResponseEntity<Map<String, Object>> getPosts(@RequestParam int page, @RequestParam int size) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof AuthDetails) {
            AuthDetails userDetails = (AuthDetails) authentication.getPrincipal();
            int userNo = userDetails.getUserNo();

            // 페이징 적용
            List<MyPagePostDTO> writtenPostList = myPageService.findWrittenPost(userNo, page, size);
            int totalPostCount= myPageService.countUserPosts(userNo);

            Map<String, Object> response = new HashMap<>();
            response.put("writtenPostList", writtenPostList);
            response.put("totalPostCount", totalPostCount);

            System.out.println("writtenPostList = " + writtenPostList);
            return ResponseEntity.ok(response); // JSON 형식으로 반환
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); // 인증되지 않은 경우 401 상태 코드 반환
        }
    }

    @GetMapping("/getComments")
    public ResponseEntity<Map<String, Object>> getComments(@RequestParam int page, @RequestParam int size) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof AuthDetails) {
            AuthDetails userDetails = (AuthDetails) authentication.getPrincipal();
            int userNo = userDetails.getUserNo();

            List<MypageCommentDTO> writtenCommentList = myPageService.findWrittenComment(userNo, page, size);
            int totalCommentCount= myPageService.countUserComment(userNo);

            Map<String, Object> response = new HashMap<>();
            response.put("writtenCommentList", writtenCommentList);
            response.put("totalCommentCount", totalCommentCount);
            System.out.println("writtenCommentList = " + writtenCommentList);
            return ResponseEntity.ok(response); // JSON 형식으로 반환
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); // 인증되지 않은 경우 401 상태 코드 반환
        }
    }

    @GetMapping("/getFavorites")
    public ResponseEntity<Map<String, Object>>  getFavorites(@RequestParam int page, @RequestParam int size) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof AuthDetails) {
            AuthDetails userDetails = (AuthDetails) authentication.getPrincipal();
            int userNo = userDetails.getUserNo();

            List<MyPagePostDTO> writtenFavoriteList = myPageService.findWrittenFavorite(userNo, page, size);

            int totalFavoriteCount= myPageService.countUserFavorite(userNo);

            Map<String, Object> response = new HashMap<>();
            response.put("writtenFavoriteList", writtenFavoriteList);
            response.put("totalFavoriteCount", totalFavoriteCount);
            return ResponseEntity.ok(response); // JSON 형식으로 반환
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); // 인증되지 않은 경우 401 상태 코드 반환
        }
    }


    @PostMapping("/deleteAccount")
    @ResponseBody
    public ResponseEntity<?> deleteAccount() {
        // 현재 인증된 사용자 정보 가져오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof AuthDetails) {
            AuthDetails userDetails = (AuthDetails) authentication.getPrincipal();
            int userNo = userDetails.getUserNo(); // 로그인한 유저의 userNo 가져오기

            try {
                // 회원 탈퇴 처리
                myPageService.deleteUserAccount(userNo);
                return ResponseEntity.ok().body("회원 탈퇴가 완료되었습니다.");
            } catch (Exception e) {
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("회원 탈퇴에 실패하였습니다.");
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("인증되지 않은 사용자입니다.");
        }
    }

    //닉네임 중복
    @GetMapping("/checknickname")
    public ResponseEntity<Map<String, Boolean>> checkNickname(@RequestParam String nickname) {
        boolean isDuplicate = myPageService.isNicknameDuplicate(nickname);
        Map<String, Boolean> response = new HashMap<>();
        response.put("isDuplicate", isDuplicate);
        return ResponseEntity.ok(response);
    }


//    -------------------------image-------------------------------

    @PostMapping("/uploadProfilePhoto")
    public ResponseEntity<Map<String, Object>> uploadProfilePhoto(@RequestParam("profilePhoto") MultipartFile file) {
        Map<String, Object> response = new HashMap<>();
        if (file.isEmpty()) {
            response.put("success", false);
            response.put("error", "파일이 선택되지 않았습니다.");
            System.out.println("파일이 선택되지 않았습니다.");
            return ResponseEntity.badRequest().body(response);
        }

        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null && authentication.getPrincipal() instanceof AuthDetails) {
                AuthDetails userDetails = (AuthDetails) authentication.getPrincipal();
                int userNo = userDetails.getUserNo();
                String userName = userDetails.getUsername();

                System.out.println("업로드 시작: userNo = " + userNo + ", userName = " + userName);

                // 파일 확장자 확인 및 저장
                String fileExtension = getFileExtension(file.getOriginalFilename());
                String fileName = userNo + "_" + userName + "." + fileExtension;
                Path filePath = Paths.get(UPLOAD_DIR + "profile/" + fileName);
                Files.createDirectories(filePath.getParent());
                Files.write(filePath, file.getBytes());

                System.out.println("파일 저장 완료: filePath = " + filePath);

                String fileUrl = "/uploads/profile/" + fileName;
                response.put("success", true);
                response.put("filePath", fileUrl);

                // 사용자 프로필 이미지 업데이트
                myPageService.updateProfileImage(userNo, fileUrl);
                System.out.println("프로필 이미지 업데이트: fileUrl = " + fileUrl);

                // 사용자 세션 업데이트
                userDetails.getLoginUserDTO().setUserImage(fileUrl);
                SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(userDetails, null, authentication.getAuthorities()));
                System.out.println("세션 정보 업데이트 완료");

                return ResponseEntity.ok(response);
            } else {
                response.put("success", false);
                response.put("error", "인증되지 않은 사용자입니다.");
                System.out.println("인증되지 않은 사용자입니다.");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            }
        } catch (IOException e) {
            e.printStackTrace();
            response.put("success", false);
            response.put("error", "파일 업로드 중 오류가 발생했습니다.");
            System.out.println("파일 업로드 중 오류가 발생했습니다: " + e.getMessage());
            return ResponseEntity.status(500).body(response);
        }
    }

    private String getFileExtension(String fileName) {
        if (fileName == null || fileName.lastIndexOf('.') == -1) {
            return "";
        }
        return fileName.substring(fileName.lastIndexOf('.') + 1).toLowerCase();
    }


    // 파일 업로드를 처리하는 핸들러 메소드
    private Map<String, String> handleFileUpload(MultipartFile file, int userNo, String userName) {
        Map<String, String> response = new HashMap<>();
        if (file.isEmpty()) {
            response.put("error", "파일이 선택되지 않았습니다.");
            return response;
        }

        String fileExtension = getFileExtension(file.getOriginalFilename());
        String fileName = userNo + "_" + userName + "." + fileExtension;
        Path filePath = Paths.get(UPLOAD_DIR + "profile/" + fileName);
        try {
            Files.createDirectories(filePath.getParent());
            Files.write(filePath, file.getBytes());
            String fileUrl = "/uploads/profile/" + fileName;
            response.put("filePath", fileUrl);
        } catch (IOException e) {
            e.printStackTrace();
            response.put("error", "파일 업로드 실패: " + e.getMessage());
        }
        return response;
    }





}
