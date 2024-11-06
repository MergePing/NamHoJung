package com.ohgiraffers.mergyping.post.model.dto;

import java.time.LocalDate;
import java.util.Date;

public class SelectPostDTO {
    private int postNo;
    private String postTitle;
    private String postCategory;
    private String postWriter;
    private String postContent;
    private LocalDate postDate;
    private int scaryNumber;
    private int commentNumber;
    private int postReport;
    private byte postImage;
    private boolean postFavorite;
    private int notScaryNumber;
    private boolean scary;
    private boolean notscary;

    public SelectPostDTO(int postNo, String postTitle, String postCategory, String postWriter, String postContent, LocalDate postDate, int scaryNumber, int commentNumber, int postReport, byte postImage, boolean postFavorite, int notScaryNumber, boolean scary, boolean notscary) {
        this.postNo = postNo;
        this.postTitle = postTitle;
        this.postCategory = postCategory;
        this.postWriter = postWriter;
        this.postContent = postContent;
        this.postDate = postDate;
        this.scaryNumber = scaryNumber;
        this.commentNumber = commentNumber;
        this.postReport = postReport;
        this.postImage = postImage;
        this.postFavorite = postFavorite;
        this.notScaryNumber = notScaryNumber;
        this.scary = scary;
        this.notscary = notscary;
    }

    public boolean isScary() {
        return scary;
    }

    public void setScary(boolean scary) {
        this.scary = scary;
    }

    public boolean isNotscary() {
        return notscary;
    }

    public void setNotscary(boolean notscary) {
        this.notscary = notscary;
    }

    public SelectPostDTO() {}

    public int getNotScaryNumber() {
        return notScaryNumber;
    }

    public void setNotScaryNumber(int notScaryNumber) {
        this.notScaryNumber = notScaryNumber;
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

    public String getPostWriter() {
        return postWriter;
    }

    public void setPostWriter(String postWriter) {
        this.postWriter = postWriter;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public LocalDate getPostDate() {
        return postDate;
    }

    public void setPostDate(LocalDate postDate) {
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

    public int getPostReport() {
        return postReport;
    }

    public void setPostReport(int postReport) {
        this.postReport = postReport;
    }

    public byte getPostImage() {
        return postImage;
    }

    public void setPostImage(byte postImage) {
        this.postImage = postImage;
    }

    public boolean isPostFavorite() {
        return postFavorite;
    }

    public void setPostFavorite(boolean postFavorite) {
        this.postFavorite = postFavorite;
    }

    @Override
    public String toString() {
        return "SelectPostDTO{" +
                "postNo=" + postNo +
                ", postTitle='" + postTitle + '\'' +
                ", postCategory='" + postCategory + '\'' +
                ", postWriter='" + postWriter + '\'' +
                ", postContent='" + postContent + '\'' +
                ", postDate=" + postDate +
                ", scaryNumber=" + scaryNumber +
                ", commentNumber=" + commentNumber +
                ", postReport=" + postReport +
                ", postImage=" + postImage +
                ", postFavorite=" + postFavorite +
                '}';
    }
}