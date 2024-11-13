package com.ohgiraffers.mergyping.user.model.dto;

import java.sql.Date;
import java.time.LocalDate;


public class AdminNoticeDetailDTO {
        private int noticeNo;
        private String title;
        private String content;
        private String category;
        private LocalDate date;
        private String userType;

        // 생성자
        public AdminNoticeDetailDTO(int noticeNo, String title, String content, String category, LocalDate date, String userType) {
            this.noticeNo = noticeNo;
            this.title = title;
            this.content = content;
            this.category = category;
            this.date = date;
            this.userType = userType;
        }

        // Getter, Setter
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

        public String getUserType() {
            return userType;
        }

        public void setUserType(String userType) {
            this.userType = userType;
        }


}