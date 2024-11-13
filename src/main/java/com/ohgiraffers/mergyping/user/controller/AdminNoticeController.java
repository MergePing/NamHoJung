package com.ohgiraffers.mergyping.user.controller;

import com.ohgiraffers.mergyping.user.model.dto.AdminNoticeDTO;
import com.ohgiraffers.mergyping.user.model.dto.AdminNoticeDetailDTO;
import com.ohgiraffers.mergyping.user.model.service.AdminNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        response.put("noticeList", notices);          // 공지사항 리스트
        response.put("currentPage", page);            // 현재 페이지
        response.put("totalPages", totalPages);       // 전체 페이지 수
        response.put("startPage", Math.max(1, page - 2)); // 페이지네이션 시작
        response.put("endPage", Math.min(totalPages, page + 2)); // 페이지네이션 끝

        return response; // JSON 형태로 반환
    }


    // 공지사항 상세 조회
    @GetMapping("/admin/notice/detail/{noticeNo}")
    public String getNoticeDetail(@PathVariable("noticeNo") String noticeNo, Model model) {
        AdminNoticeDetailDTO noticeDetail = adminNoticeService.getNoticeDetail(noticeNo);
        model.addAttribute("noticeDetail", noticeDetail);
        return "user/admin/adminnoticedetail";
    }

    // 공지사항 수정 요청을 처리하는 메서드
    @PostMapping("/admin/notice/detail/edit/{noticeNo}")
    @ResponseBody
    public Map<String, Object> updateNotice(@PathVariable("noticeNo") String noticeNo,
                                            @RequestBody AdminNoticeDetailDTO noticeDetailDTO) {
        noticeDetailDTO.setNoticeNo(Integer.parseInt(noticeNo)); // noticeNo 설정
        boolean updateSuccess = adminNoticeService.updateNotice(noticeDetailDTO);

        Map<String, Object> response = new HashMap<>();
        response.put("success", updateSuccess);
        response.put("message", updateSuccess ? "공지사항이 수정되었습니다." : "공지사항 수정에 실패했습니다.");
        return response;
    }

    // 공지사항 삭제 요청을 처리하는 메서드
    @DeleteMapping("/admin/notice/detail/delete/{noticeNo}")
    @ResponseBody
    public Map<String, Object> deleteNotice(@PathVariable("noticeNo") String noticeNo) {
        boolean deleteSuccess = adminNoticeService.deleteNotice(noticeNo);

        Map<String, Object> response = new HashMap<>();
        response.put("success", deleteSuccess);
        response.put("message", deleteSuccess ? "공지사항이 삭제되었습니다." : "공지사항 삭제에 실패했습니다.");
        return response;
    }

    // 공지사항 추가 요청을 처리하는 메서드
    @PostMapping("/admin/notice/add")
    @ResponseBody
    public Map<String, Object> addNotice(@RequestBody AdminNoticeDTO noticeDTO) {
        boolean addSuccess = adminNoticeService.addNotice(noticeDTO);

        Map<String, Object> response = new HashMap<>();
        response.put("success", addSuccess);
        response.put("message", addSuccess ? "공지사항이 추가되었습니다." : "공지사항 추가에 실패했습니다.");
        return response;
    }

    // 검색 요청 처리

    @GetMapping("/admin/notice/search")
    @ResponseBody
    public Map<String, Object> searchNotices(@RequestParam("keyword") String keyword,
                                             @RequestParam(defaultValue = "1") int page,
                                             @RequestParam(defaultValue = "7") int pageSize) {
        List<AdminNoticeDTO> notices = adminNoticeService.searchNoticesByTitle(keyword, page, pageSize);
        int totalNotices = adminNoticeService.countNoticesByKeyword(keyword); // 검색 결과 개수
        int totalPages = (int) Math.ceil((double) totalNotices / pageSize);

        Map<String, Object> response = new HashMap<>();
        response.put("noticeList", notices);          // 검색 결과 리스트
        response.put("currentPage", page);            // 현재 페이지
        response.put("totalPages", totalPages);       // 전체 페이지 수

        return response; // JSON 형태로 반환
    }
}