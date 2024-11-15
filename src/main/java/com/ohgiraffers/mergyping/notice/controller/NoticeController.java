package com.ohgiraffers.mergyping.notice.controller;

import com.ohgiraffers.mergyping.notice.model.dto.NoticeDTO;
import com.ohgiraffers.mergyping.notice.model.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/notice")
public class NoticeController {

    private final NoticeService noticeService;

    @Autowired
    public NoticeController(NoticeService noticeService) {
        this.noticeService = noticeService;
    }

    @GetMapping
    public String showNotices(@RequestParam(defaultValue = "1") int page,
                              @RequestParam(defaultValue = "7") int pageSize,
                              Model model) {
        List<NoticeDTO> notices = noticeService.getNoticesByPage(page, pageSize);
        int totalNotices = noticeService.countNotices();
        int totalPages = (int) Math.ceil((double) totalNotices / pageSize);

        model.addAttribute("notices", notices);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("startPage", Math.max(1, page - 2));
        model.addAttribute("endPage", Math.min(totalPages, page + 2));




        return "/notice/notice";
    }



}