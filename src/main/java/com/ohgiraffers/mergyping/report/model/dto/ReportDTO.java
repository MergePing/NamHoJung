package com.ohgiraffers.mergyping.report.model.dto;

import java.time.LocalDate;

public class ReportDTO {

    private int reportNo;
    private String reportReason;
    private LocalDate reportDate;
    private int userNo;
    private String thisName;
    private Integer postNo;
    private Integer commentNo;


    public ReportDTO () {}

    public ReportDTO(int reportNo, String reportReason, LocalDate reportDate, int userNo, String thisName, Integer postNo, Integer commentNo) {
        this.reportNo = reportNo;
        this.reportReason = reportReason;
        this.reportDate = reportDate;
        this.userNo = userNo;
        this.thisName = thisName;
        this.postNo = postNo;
        this.commentNo = commentNo;
    }


    public int getReportNo() {
        return reportNo;
    }

    public void setReportNo(int reportNo) {
        this.reportNo = reportNo;
    }

    public String getReportReason() {
        return reportReason;
    }

    public void setReportReason(String reportReason) {
        this.reportReason = reportReason;
    }

    public LocalDate getReportDate() {
        return reportDate;
    }

    public void setReportDate(LocalDate reportDate) {
        this.reportDate = reportDate;
    }

    public int getUserNo() {
        return userNo;
    }

    public void setUserNo(int userNo) {
        this.userNo = userNo;
    }

    public String getThisName() {
        return thisName;
    }

    public void setThisName(String thisName) {
        this.thisName = thisName;
    }

    public Integer getPostNo() {
        return postNo;
    }

    public void setPostNo(Integer postNo) {
        this.postNo = postNo;
    }

    public Integer getCommentNo() {
        return commentNo;
    }

    public void setCommentNo(Integer commentNo) {
        this.commentNo = commentNo;
    }
}
