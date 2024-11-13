package com.ohgiraffers.mergyping.user.model.dto;

import java.time.LocalDate;

public class AdminNoticeDTO {
    private int noticeNo;
    private String title;
    private String category;
    private String content;
    private LocalDate date;
    private String userType;


    public AdminNoticeDTO() {
    }


    public AdminNoticeDTO(int noticeNo, String title, String category, String content, LocalDate date, String userType) {
        this.noticeNo = noticeNo;
        this.title = title;
        this.category = category;
        this.content = content;
        this.date = date;
        this.userType = userType;
    }

    public int getNoticeNo() {
        return noticeNo;
    }

    public void setNoticeNo(int noticeNo) {
        this.noticeNo = noticeNo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}