package com.ohgiraffers.mergyping.notice.model.dto;

import java.time.LocalDate;

public class NoticeDTO {

    private int noticeNo;
    private String category;
    private String title;
    private String content;
    private LocalDate noticeDate;



    public NoticeDTO () {}

    public NoticeDTO(int noticeNo, String category, String title, String content, LocalDate noticeDate) {
        this.noticeNo = noticeNo;
        this.category = category;
        this.title = title;
        this.content = content;
        this.noticeDate = noticeDate;
    }


    public int getNoticeNo() {
        return noticeNo;
    }

    public void setNoticeNo(int noticeNo) {
        this.noticeNo = noticeNo;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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

    public LocalDate getNoticeDate() {
        return noticeDate;
    }

    public void setNoticeDate(LocalDate noticeDate) {
        this.noticeDate = noticeDate;
    }
}


