package com.ohgiraffers.mergyping.report.model.service;

import com.ohgiraffers.mergyping.report.model.dao.ReportMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportService {

    private final ReportMapper reportMapper;

    @Autowired
    public ReportService(ReportMapper reportMapper) {
        this.reportMapper = reportMapper;
    }

    public boolean addReport(String reportReason, int userNo, String thisName, Integer postNo, Integer commentNo) {
        return reportMapper.insertReport(reportReason, userNo, thisName, postNo, commentNo) > 0;
    }
}