package com.ohgiraffers.mergyping.notice.controller;

import com.ohgiraffers.mergyping.auth.model.AuthDetails;
import com.ohgiraffers.mergyping.notice.model.dto.NoticeDTO;
import com.ohgiraffers.mergyping.notice.model.dto.NoticeDetailDTO;
import com.ohgiraffers.mergyping.notice.model.service.NoticeService;
import com.ohgiraffers.mergyping.user.model.dto.MyPageDTO;
import com.ohgiraffers.mergyping.user.model.service.MyPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/notice")
public class NoticeController {

    private final NoticeService noticeService;
    private final MyPageService myPageService;




    @Autowired
    public NoticeController(NoticeService noticeService, MyPageService myPageService) {
        this.noticeService = noticeService;
        this.myPageService = myPageService;
    }

    // 공지사항 리스트 페이지 반환
    @GetMapping
    public String showNoticeList(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof AuthDetails) {
            AuthDetails userDetails = (AuthDetails) authentication.getPrincipal();
            int userNo = userDetails.getUserNo();

            MyPageDTO userInfo = myPageService.findUserInfo(userNo);
            model.addAttribute("userInfo", userInfo);


            // 누적된 출석 수 가져오기
            Integer attendanceCount = myPageService.getUserAttendanceCount(userNo);
            model.addAttribute("attendanceCount", attendanceCount);

            // 등급 기준 정해주기
            int levelNo = myPageService.calculateLevel(attendanceCount);
            System.out.println("levelNo = " + levelNo);

            // 등급 기준과 출석수 기반으로 등급 업데이트하기
            myPageService.updateUserLevel(userNo, levelNo);

            // 유저의 등급 가져오기
            String levelName = myPageService.getLevelName(levelNo);
            model.addAttribute("userLevel", levelName);

            // 유저의 다음 레벨 이름 가져오기
            String nextLevelName = myPageService.getNextLevelName(levelNo);
            model.addAttribute("nextLevelName", nextLevelName);

            // 다음 등급에 필요한 출석 횟수 조회
            int nextLevelRequiredAttendance = myPageService.getNextLevelRequiredAttendance(levelNo, attendanceCount);
            model.addAttribute("nextLevelRequiredAttendance", nextLevelRequiredAttendance);


            // MyPageDTO에 userNo를 전달하여 사용자 정보를 가져옵니다.
            MyPageDTO myPageDTO = myPageService.findNickName(userNo);
            model.addAttribute("myPageDTO", myPageDTO);




            Map<String, Object> mbtiInfo = myPageService.findUserMBTIInfo(userNo);
            model.addAttribute("mbtiInfo", mbtiInfo);
        }



        return "notice/notice"; // 공지사항 리스트 페이지 반환
    }

    // AJAX 요청: 공지사항 리스트 JSON 반환
    @GetMapping("/data")
    @ResponseBody
    public Map<String, Object> getNoticeListData(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "7") int pageSize) {

        List<NoticeDTO> notices = noticeService.getNoticesByPage(page, pageSize);
        int totalNotices = noticeService.countNotices();
        int totalPages = (int) Math.ceil((double) totalNotices / pageSize);

        Map<String, Object> response = new HashMap<>();
        response.put("noticeList", notices);
        response.put("currentPage", page);
        response.put("totalPages", totalPages);

        return response; // JSON 형식으로 공지사항 데이터 반환
    }

    // 공지사항 상세 페이지 반환
    @GetMapping("/detail/{noticeNo}")
    public String getNoticeDetail(@PathVariable("noticeNo") String noticeNo, Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof AuthDetails) {
            AuthDetails userDetails = (AuthDetails) authentication.getPrincipal();
            int userNo = userDetails.getUserNo();

            MyPageDTO userInfo = myPageService.findUserInfo(userNo);
            model.addAttribute("userInfo", userInfo);


            // 누적된 출석 수 가져오기
            Integer attendanceCount = myPageService.getUserAttendanceCount(userNo);
            model.addAttribute("attendanceCount", attendanceCount);

            // 등급 기준 정해주기
            int levelNo = myPageService.calculateLevel(attendanceCount);
            System.out.println("levelNo = " + levelNo);

            // 등급 기준과 출석수 기반으로 등급 업데이트하기
            myPageService.updateUserLevel(userNo, levelNo);

            // 유저의 등급 가져오기
            String levelName = myPageService.getLevelName(levelNo);
            model.addAttribute("userLevel", levelName);

            // 유저의 다음 레벨 이름 가져오기
            String nextLevelName = myPageService.getNextLevelName(levelNo);
            model.addAttribute("nextLevelName", nextLevelName);

            // 다음 등급에 필요한 출석 횟수 조회
            int nextLevelRequiredAttendance = myPageService.getNextLevelRequiredAttendance(levelNo, attendanceCount);
            model.addAttribute("nextLevelRequiredAttendance", nextLevelRequiredAttendance);


            // MyPageDTO에 userNo를 전달하여 사용자 정보를 가져옵니다.
            MyPageDTO myPageDTO = myPageService.findNickName(userNo);
            model.addAttribute("myPageDTO", myPageDTO);




            Map<String, Object> mbtiInfo = myPageService.findUserMBTIInfo(userNo);
            model.addAttribute("mbtiInfo", mbtiInfo);
        }

        NoticeDetailDTO noticeDetail = noticeService.getNoticeDetail(noticeNo);
        model.addAttribute("noticeDetail", noticeDetail);
        return "notice/selectnotice"; // 공지사항 상세 페이지 반환
    }
}