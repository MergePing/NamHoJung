package com.ohgiraffers.mergyping.report.controller;

import com.ohgiraffers.mergyping.report.model.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/post")
public class ReportController {

    private final ReportService reportService;

    @Autowired
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @PostMapping("/report")
    public ResponseEntity<Map<String, Object>> reportPost(@RequestBody Map<String, Object> reportData) {
        String reportReason = (String) reportData.get("reason");
        Integer userNo = (Integer) reportData.get("userNo");
        String thisName = (String) reportData.get("thisName");
        Integer postNo = (Integer) reportData.get("postNo");
        Integer commentNo = (Integer) reportData.get("commentNo");

        // Null 체크
        if (reportReason == null || userNo == null || postNo == null) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("message", "필수 데이터가 누락되었습니다.");
            return ResponseEntity.badRequest().body(errorResponse);
        }

        boolean success = reportService.addReport(reportReason, userNo, thisName, postNo, commentNo);

        Map<String, Object> response = new HashMap<>();
        response.put("success", success);
        response.put("message", success ? "신고가 접수되었습니다." : "신고 접수에 실패했습니다.");

        return ResponseEntity.ok(response);
    }
}