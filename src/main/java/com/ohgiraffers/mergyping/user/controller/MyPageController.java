package com.ohgiraffers.mergyping.user.controller;

import com.ohgiraffers.mergyping.auth.model.AuthDetails;
import com.ohgiraffers.mergyping.post.model.dto.SelectPostDTO;
import com.ohgiraffers.mergyping.post.model.service.PostService;
import com.ohgiraffers.mergyping.user.model.dto.MyPageDTO;
import com.ohgiraffers.mergyping.user.model.dto.MyPagePostDTO;
import com.ohgiraffers.mergyping.user.model.dto.MypageCommentDTO;
import com.ohgiraffers.mergyping.user.model.service.MyPageService;
import com.ohgiraffers.mergyping.user.model.service.UserService;
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

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MyPageController {

    private final MyPageService myPageService;
    private final PasswordEncoder passwordEncoder;

    // 이미지가 저장될 경로
    private static final String UPLOAD_DIR = "src/main/resources/static/profile/";

    // 이미지 파일만 받기 위한 허용되는 확장자 목록
    private static final List<String> ALLOWED_EXTENSIONS = Arrays.asList("jpg", "jpeg", "png","gif");
    private final UserService userService;
    private PostService postService;


    @Autowired
    public MyPageController(MyPageService myPageService, PasswordEncoder passwordEncoder, PostService postService, UserService userService) {
        this.myPageService = myPageService;
        this.passwordEncoder = passwordEncoder;
        this.postService=postService;
        this.userService = userService;
    }

    @GetMapping("/userinfo")
    public String findUserInfo(Model model) throws IOException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof AuthDetails) {
            AuthDetails userDetails = (AuthDetails) authentication.getPrincipal();
            int userNo = userDetails.getUserNo();

            // MyPageDTO에 userNo를 전달하여 사용자 정보를 가져옴
            MyPageDTO myPageDTO = myPageService.findNickName(userNo);
            model.addAttribute("myPageDTO", myPageDTO);

            MyPageDTO myPageDTO2 = myPageService.findEmail(userNo);
            model.addAttribute("myPageDTO2", myPageDTO2);

            MyPageDTO myPageDTO3 = myPageService.findId(userNo);
            model.addAttribute("myPageDTO3", myPageDTO3);
        } else {
            // 인증되지 않은 경우 로그인 페이지로 리다이렉트
            return "redirect:/login";
        }

        return "user/mypage/userinfo"; // userinfo.html로 이동
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

//    ------------------image------------------------

    @PostMapping("/uploadprofile")
    @ResponseBody
    public Map<String, String> uploadProfileImage(@RequestParam("file") MultipartFile file,int userNo) {

        // 파일이라는 매개변수를 받아서 첫번째 이미지를 업로드 하는 핸들러메소드 호출
        return handleFileUpload(file, userNo);
    }

    private Map<String, String> handleFileUpload(MultipartFile file,int userNo) {

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
            String filePath = saveFile(file,userNo);

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

    private String saveFile(MultipartFile file, int userNo) throws IOException {
        // 오늘 날짜를 yyyy-mm-dd 형식으로 포맷
        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        // 파일 확장자 추출 아래 getFileExtension 메소드 참고
        //file.getOriginalFilename()는 img1.png 반환, getFileExtension(img1.png)는 png 반환
        String fileExtension = getFileExtension(file.getOriginalFilename());
        System.out.println("111111111111111"+fileExtension);

        // 파일 이름을 위에서 설정한 날짜, 게시글 번호, 이미지 번호. 추출한 확장자로 바꿈 ex) 2024-11-08_13_1.jpg
        String fileName = date + "_" + userNo +  "." + fileExtension;

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



    @PostMapping("/info")
    public ResponseEntity<Map<String, String>> ChangImage(
            @RequestParam("userNo") int userNo,
            @RequestParam(value = "file", required = false) MultipartFile file) {


        //응답을 받을 맵 생성
        Map<String, String> response = new HashMap<>();
        try {
            // 새로 생성된 게시물의 데이터들을 저장할 새로운 selectPostDTO 생성, setter로 값 주입
            SelectPostDTO selectPostDTO = new SelectPostDTO();
            selectPostDTO.setPostNo(userNo);

            // 파일이 null이 아니고 비어있지 않은 경우 파일, 게시글 번호, 이미지번호를 저장
            if (file != null && !file.isEmpty()) {
                String firstImagePath = saveFile(file,userNo);

                // 이미지의 확장자 주입
                String FileExtension = getFileExtension(file.getOriginalFilename());
                selectPostDTO.setPostImageFirstExtension(FileExtension);
                System.out.println("FileExtension = " + FileExtension);

                // 첫번째 파일에 클라이언트가 접근할수있는 경로 주입
                selectPostDTO.setPostImageFirst("/uploads/"+firstImagePath);
            }else {
                System.out.println("2222222222fileFirst is null or empty");
            }


            //게시물을 생성하는서비스 메소드 호출
            userService.updateUserProfileImage(selectPostDTO.getPostImageFirst(), userNo);

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






            Map<String, Object> mbtiInfo = myPageService.findUserMBTIInfo(userNo);
            model.addAttribute("mbtiInfo", mbtiInfo);

        } else {
            // 인증되지 않은 경우 로그인 페이지로 리다이렉트
            return "redirect:/login";
        }

        return "user/mypage/useractive";
    }


    @GetMapping("/getPosts")
    public ResponseEntity<List<MyPagePostDTO>> getPosts() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof AuthDetails) {
            AuthDetails userDetails = (AuthDetails) authentication.getPrincipal();
            int userNo = userDetails.getUserNo();

            List<MyPagePostDTO> writtenPostList = myPageService.findWrittenPost(userNo);
            return ResponseEntity.ok(writtenPostList); // JSON 형식으로 반환
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); // 인증되지 않은 경우 401 상태 코드 반환
        }
    }

    @GetMapping("/getComments")
    public ResponseEntity<List<MypageCommentDTO>> getComments() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof AuthDetails) {
            AuthDetails userDetails = (AuthDetails) authentication.getPrincipal();
            int userNo = userDetails.getUserNo();

            List<MypageCommentDTO> writtenCommentList = myPageService.findWrittenComment(userNo);
            return ResponseEntity.ok(writtenCommentList); // JSON 형식으로 반환
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); // 인증되지 않은 경우 401 상태 코드 반환
        }
    }

    @GetMapping("/getFavorites")
    public ResponseEntity<List<MyPagePostDTO>> getFavorites() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof AuthDetails) {
            AuthDetails userDetails = (AuthDetails) authentication.getPrincipal();
            int userNo = userDetails.getUserNo();

            List<MyPagePostDTO> writtenFavoriteList = myPageService.findWrittenFavorite(userNo);
            return ResponseEntity.ok(writtenFavoriteList); // JSON 형식으로 반환
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








}
