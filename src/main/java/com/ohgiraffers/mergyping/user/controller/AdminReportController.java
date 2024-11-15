package com.ohgiraffers.mergyping.user.controller;

import com.ohgiraffers.mergyping.user.model.dto.AdminReportDTO;
import com.ohgiraffers.mergyping.user.model.service.AdminReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AdminReportController {

    private final AdminReportService adminReportService;

    @Autowired
    public AdminReportController(AdminReportService adminReportService) {
        this.adminReportService = adminReportService;
    }

    // 신고 관리 페이지 렌더링
    @GetMapping("/admin/report")
    public String showReportList(@RequestParam(defaultValue = "1") int page,
                                 @RequestParam(defaultValue = "7") int pageSize,
                                 Model model) {
        // 신고 목록 및 페이징 정보 가져오기
        List<AdminReportDTO> reports = adminReportService.getReportsByPage(page, pageSize);
        int totalReports = adminReportService.countReports();
        int totalPages = (int) Math.ceil((double) totalReports / pageSize);

        // Model에 데이터 추가
        model.addAttribute("reportList", reports);         // 신고 목록
        model.addAttribute("currentPage", page);           // 현재 페이지
        model.addAttribute("totalPages", totalPages);      // 총 페이지 수
        model.addAttribute("startPage", Math.max(1, page - 2)); // 페이지네이션 시작
        model.addAttribute("endPage", Math.min(totalPages, page + 2)); // 페이지네이션 끝

        return "/user/admin/adminreport"; // 신고 관리 HTML 페이지 반환
    }

    // AJAX 요청으로 신고 목록을 JSON 형식으로 반환
    @GetMapping("/admin/report/data")
    @ResponseBody
    public Map<String, Object> getReportListData(@RequestParam(defaultValue = "1") int page,
                                                 @RequestParam(defaultValue = "7") int pageSize) {
        List<AdminReportDTO> reports = adminReportService.getReportsByPage(page, pageSize);
        int totalReports = adminReportService.countReports();
        int totalPages = (int) Math.ceil((double) totalReports / pageSize);

        Map<String, Object> response = new HashMap<>();
        response.put("reportList", reports);          // 신고 목록
        response.put("currentPage", page);            // 현재 페이지
        response.put("totalPages", totalPages);       // 총 페이지 수
        return response; // JSON 형태로 반환
    }

    @DeleteMapping("/admin/report/delete/{reportNo}")
    @ResponseBody
    public ResponseEntity<Void> deleteReport(@PathVariable int reportNo) {
        boolean isDeleted = adminReportService.deleteReport(reportNo);
        if (isDeleted) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}