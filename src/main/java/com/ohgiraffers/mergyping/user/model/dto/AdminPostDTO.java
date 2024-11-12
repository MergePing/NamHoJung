package com.ohgiraffers.mergyping.user.model.dto;

import java.time.LocalDate;

public class AdminPostDTO {
    private int postNo;
    private String postTitle;
    private String postCategory;
    private LocalDate postDate; // 변경: LocalDate로 정의
    private int scaryNumber;
    private int commentNumber;

    public AdminPostDTO() {}

    public AdminPostDTO(int postNo, String postTitle, String postCategory, LocalDate postDate, int scaryNumber, int commentNumber) {
        this.postNo = postNo;
        this.postTitle = postTitle;
        this.postCategory = postCategory;
        this.postDate = postDate;
        this.scaryNumber = scaryNumber;
        this.commentNumber = commentNumber;
    }

    public int getPostNo() {
        return postNo;
    }

    public void setPostNo(int postNo) {
        this.postNo = postNo;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getPostCategory() {
        return postCategory;
    }

    public void setPostCategory(String postCategory) {
        this.postCategory = postCategory;
    }

    public LocalDate getPostDate() {
        return postDate; // LocalDate로 변경된 getter
    }

    public void setPostDate(LocalDate postDate) { // LocalDate로 변경된 setter
        this.postDate = postDate;
    }

    public int getScaryNumber() {
        return scaryNumber;
    }

    public void setScaryNumber(int scaryNumber) {
        this.scaryNumber = scaryNumber;
    }

    public int getCommentNumber() {
        return commentNumber;
    }

    public void setCommentNumber(int commentNumber) {
        this.commentNumber = commentNumber;
    }

    @Override
    public String toString() {
        return "AdminPostDTO{" +
                "postNo=" + postNo +
                ", postTitle='" + postTitle + '\'' +
                ", postCategory='" + postCategory + '\'' +
                ", postDate=" + postDate +
                ", scaryNumber=" + scaryNumber +
                ", commentNumber=" + commentNumber +
                '}';
    }
}