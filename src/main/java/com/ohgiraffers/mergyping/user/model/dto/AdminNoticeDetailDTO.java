package com.ohgiraffers.mergyping.user.model.dto;

import java.time.LocalDate;

public class AdminNoticeDetailDTO {

    private int noticeNo;
    private String title;
    private String content;
    private LocalDate date;
    private String category;

    public AdminNoticeDetailDTO(int noticeNo, String title, String content, LocalDate date, String category) {
        this.noticeNo = noticeNo;
        this.title = title;
        this.content = content;
        this.date = date;
        this.category = category;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}