package com.ohgiraffers.mergyping.user.model.service;

import com.ohgiraffers.mergyping.user.model.dao.AdminReportMapper;
import com.ohgiraffers.mergyping.user.model.dto.AdminReportDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdminReportService {

    private final AdminReportMapper adminReportMapper;

    @Autowired
    public AdminReportService(AdminReportMapper adminReportMapper) {
        this.adminReportMapper = adminReportMapper;
    }

    // 페이징 처리된 신고 목록 가져오기
    public List<AdminReportDTO> getReportsByPage(int page, int pageSize) {
        int offset = (page - 1) * pageSize; // 오프셋 계산
        Map<String, Object> params = new HashMap<>();
        params.put("offset", offset);
        params.put("pageSize", pageSize);

        return adminReportMapper.selectReportsByPage(params); // Mapper 호출
    }

    // 전체 신고 개수 가져오기
    public int countReports() {
        return adminReportMapper.countReports(); // Mapper 호출
    }
}