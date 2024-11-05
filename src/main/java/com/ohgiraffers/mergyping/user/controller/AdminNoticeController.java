package com.ohgiraffers.mergyping.user.controller;

import com.ohgiraffers.mergyping.user.model.dto.AdminNoticeDTO;
import com.ohgiraffers.mergyping.user.model.dto.AdminNoticeDetailDTO;
import com.ohgiraffers.mergyping.user.model.service.AdminNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AdminNoticeController {

    private final AdminNoticeService adminNoticeService;

    @Autowired
    public AdminNoticeController(AdminNoticeService adminNoticeService) {
        this.adminNoticeService = adminNoticeService;
    }

    // 초기 로딩 시 공지사항 리스트 페이지를 보여줍니다 (URL 변경 없음)
    @GetMapping("/admin/notice")
    public String showNoticeList(Model model) {
        int page = 1; // 초기 로딩 시 기본 페이지 번호
        int pageSize = 7;

        List<AdminNoticeDTO> notices = adminNoticeService.getNoticesByPage(page, pageSize);
        int totalNotices = adminNoticeService.countNotices();
        int totalPages = (int) Math.ceil((double) totalNotices / pageSize);

        model.addAttribute("noticeList", notices);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("startPage", Math.max(1, page - 2));
        model.addAttribute("endPage", Math.min(totalPages, page + 2));

        return "/user/admin/adminnotice"; // 공지사항 페이지 뷰 로드
    }

    // AJAX 요청으로 공지사항 리스트를 JSON 형식으로 반환
    @GetMapping("/admin/notice/data")
    @ResponseBody
    public Map<String, Object> getNoticeListData(@RequestParam(defaultValue = "1") int page,
                                                 @RequestParam(defaultValue = "7") int pageSize) {
        List<AdminNoticeDTO> notices = adminNoticeService.getNoticesByPage(page, pageSize);
        int totalNotices = adminNoticeService.countNotices();
        int totalPages = (int) Math.ceil((double) totalNotices / pageSize);

        Map<String, Object> response = new HashMap<>();
        response.put("noticeList", notices);
        response.put("currentPage", page);
        response.put("totalPages", totalPages);
        response.put("startPage", Math.max(1, page - 2));
        response.put("endPage", Math.min(totalPages, page + 2));

        return response; // 페이지네이션 데이터 JSON 반환
    }

    @GetMapping("/admin/notice/detail/{noticeNo}")
    public String getNoticeDetail(@PathVariable("noticeNo") String noticeNo, Model model) {

        AdminNoticeDetailDTO noticeDetail = adminNoticeService.getNoticeDetail(noticeNo);
        model.addAttribute("noticeDetail", noticeDetail);


        return "user/admin/adminnoticedetail";
    }
}
