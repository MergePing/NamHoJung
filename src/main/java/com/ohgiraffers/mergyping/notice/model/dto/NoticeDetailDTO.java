package com.ohgiraffers.mergyping.notice.model.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class NoticeDetailDTO {

    private int noticeNo;          // 공지사항 번호
    private String title;          // 공지사항 제목
    private String content;        // 공지사항 내용
    private String category;       // 공지사항 카테고리 (예: 공지, 알림)
    private LocalDate date;    // 공지사항 등록 날짜

    // 기본 생성자
    public NoticeDetailDTO() {}

    public NoticeDetailDTO(int noticeNo, String title, String content, String category, LocalDate date) {
        this.noticeNo = noticeNo;
        this.title = title;
        this.content = content;
        this.category = category;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}