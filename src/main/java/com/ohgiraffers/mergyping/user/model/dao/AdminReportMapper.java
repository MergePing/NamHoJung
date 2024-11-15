package com.ohgiraffers.mergyping.user.model.dao;

import com.ohgiraffers.mergyping.user.model.dto.AdminReportDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface AdminReportMapper {

    // 페이징 처리된 신고 목록 조회
    List<AdminReportDTO> selectReportsByPage(Map<String, Object> params);

    // 전체 신고 개수 조회
    int countReports();

    // 신고 삭제 메서드
    int deleteReport(int reportNo);
}