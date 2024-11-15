package com.ohgiraffers.mergyping.notice.controller;

import com.ohgiraffers.mergyping.notice.model.dto.NoticeDTO;
import com.ohgiraffers.mergyping.notice.model.dto.NoticeDetailDTO;
import com.ohgiraffers.mergyping.notice.model.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    public NoticeController(NoticeService noticeService) {
        this.noticeService = noticeService;
    }

    // 공지사항 리스트 페이지 반환
    @GetMapping
    public String showNoticeList() {
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
        NoticeDetailDTO noticeDetail = noticeService.getNoticeDetail(noticeNo);
        model.addAttribute("noticeDetail", noticeDetail);
        return "notice/selectnotice"; // 공지사항 상세 페이지 반환
    }
}