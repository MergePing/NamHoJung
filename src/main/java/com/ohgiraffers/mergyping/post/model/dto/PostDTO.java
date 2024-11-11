package com.ohgiraffers.mergyping.post.model.dto;

import java.time.LocalDate;
import java.util.Date;


public class PostDTO {
    private int postNo;
    private String postTitle;
    private String postCategory;
    private LocalDate postDate;
    private int scaryNumber;
    private int commentNumber;
    private boolean postFavorite;
    private String formattedDate;
    private String postImageFirstExtension;
    private String postImageSecondExtension;

    public PostDTO() {}

    public PostDTO(int postNo, String postTitle, String postCategory, LocalDate postDate, int scaryNumber, int commentNumber, boolean postFavorite, String formattedDate, String postImageFirstExtension, String postImageSecondExtension) {
        this.postNo = postNo;
        this.postTitle = postTitle;
        this.postCategory = postCategory;
        this.postDate = postDate;
        this.scaryNumber = scaryNumber;
        this.commentNumber = commentNumber;
        this.postFavorite = postFavorite;
        this.formattedDate = formattedDate;
        this.postImageFirstExtension = postImageFirstExtension;
        this.postImageSecondExtension = postImageSecondExtension;
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

    public boolean isPostFavorite() {
        return postFavorite;
    }

    public void setPostFavorite(boolean postFavorite) {
        this.postFavorite = postFavorite;
    }

    public String getFormattedDate() {
        return formattedDate;
    }

    public void setFormattedDate(String formattedDate) {
        this.formattedDate = formattedDate;
    }

    public String getPostImageFirstExtension() {
        return postImageFirstExtension;
    }

    public void setPostImageFirstExtension(String postImageFirstExtension) {
        this.postImageFirstExtension = postImageFirstExtension;
    }

    public String getPostImageSecondExtension() {
        return postImageSecondExtension;
    }

    public void setPostImageSecondExtension(String postImageSecondExtension) {
        this.postImageSecondExtension = postImageSecondExtension;
    }

    @Override
    public String toString() {
        return "PostDTO{" +
                "postNo=" + postNo +
                ", postTitle='" + postTitle + '\'' +
                ", postCategory='" + postCategory + '\'' +
                ", postDate=" + postDate +
                ", scaryNumber=" + scaryNumber +
                ", commentNumber=" + commentNumber +
                ", postFavorite=" + postFavorite +
                ", formattedDate='" + formattedDate + '\'' +
                ", postImageFirstExtension='" + postImageFirstExtension + '\'' +
                ", postImageSecondExtension='" + postImageSecondExtension + '\'' +
                '}';
    }
}