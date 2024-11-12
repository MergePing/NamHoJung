package com.ohgiraffers.mergyping.user.model.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class AdminPostDTO {
    private int postNo;
    private String postTitle;
    private String postCategory;
    private LocalDateTime postDate; // 변경: LocalDate로 정의
    private int scaryNumber;
    private int commentNumber;

    public AdminPostDTO() {
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

    public LocalDateTime getPostDate() {
        return postDate;
    }

    public void setPostDate(LocalDateTime postDate) {
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

    public AdminPostDTO(int postNo, String postTitle, String postCategory, LocalDateTime postDate, int scaryNumber, int commentNumber) {
        this.postNo = postNo;
        this.postTitle = postTitle;
        this.postCategory = postCategory;
        this.postDate = postDate;
        this.scaryNumber = scaryNumber;
        this.commentNumber = commentNumber;



    }
}