package com.ohgiraffers.mergyping.notice.controller;

import com.ohgiraffers.mergyping.notice.model.dto.NoticeDTO;
import com.ohgiraffers.mergyping.notice.model.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @GetMapping
    public String showNoticePage() {
        return "notice/notice-list"; // 공지사항 리스트 HTML 파일
    }

    @GetMapping("/data")
    @ResponseBody
    public Map<String, Object> getNotices(@RequestParam(defaultValue = "1") int page,
                                          @RequestParam(defaultValue = "7") int pageSize) {
        List<NoticeDTO> notices = noticeService.getNoticesByPage(page, pageSize);
        int totalNotices = noticeService.countNotices();
        int totalPages = (int) Math.ceil((double) totalNotices / pageSize);

        Map<String, Object> response = new HashMap<>();
        response.put("noticeList", notices);          // 공지사항 리스트
        response.put("currentPage", page);            // 현재 페이지
        response.put("totalPages", totalPages);       // 전체 페이지 수
        return response; // JSON 반환
    }
}