package com.ohgiraffers.mergyping.user.model.dto;

import java.time.LocalDate;

public class AdminReportDTO {
    private int reportNo;
    private String reportReason;
    private LocalDate reportDate;
    private int userNo;
    private String reportedUserId;
    private String thisName;
    private Integer postNo;
    private Integer commentNo;

    public AdminReportDTO() {}


    public AdminReportDTO(int reportNo, String reportReason, LocalDate reportDate, int userNo, String reportedUserId, String thisName, Integer postNo, Integer commentNo) {
        this.reportNo = reportNo;
        this.reportReason = reportReason;
        this.reportDate = reportDate;
        this.userNo = userNo;
        this.reportedUserId = reportedUserId;
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

    public String getReportedUserId() {
        return reportedUserId;
    }

    public void setReportedUserId(String reportedUserId) {
        this.reportedUserId = reportedUserId;
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

