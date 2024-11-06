package com.ohgiraffers.mergyping.user.model.dto;

import java.sql.Date;

public class AdminNoticeDetailDTO {

    private int noticeNo;
    private String title;
    private String content;
    private Date date;


    public AdminNoticeDetailDTO(int noticeNo, String title, String content, Date date) {
        this.noticeNo = noticeNo;
        this.title = title;
        this.content = content;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}