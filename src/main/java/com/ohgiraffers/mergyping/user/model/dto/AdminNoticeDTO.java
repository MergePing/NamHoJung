package com.ohgiraffers.mergyping.user.model.dto;

import java.time.LocalDate;

public class AdminNoticeDTO {

    private int noticeNo;
    private String title;
    private LocalDate date;
    public String category;

    public AdminNoticeDTO() {}

    public AdminNoticeDTO(int noticeNo, String title, LocalDate date, String category) {
        this.noticeNo = noticeNo;
        this.title = title;
        this.date = date;
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

}
