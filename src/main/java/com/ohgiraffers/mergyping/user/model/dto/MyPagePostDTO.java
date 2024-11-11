package com.ohgiraffers.mergyping.user.model.dto;

import java.time.LocalDate;
import java.util.Date;

public class MyPagePostDTO {
//     <result property="postNo" column="POST_NO"/>
//        <result property="postFavorite" column="POST_FAVORITE"/>
//        <result property="postTitle" column="POST_TITLE"/>
//        <result property="postCategory" column="POST_CATEGORY"/>
//        <result property="postDate" column="POST_DATE"/>
//        <result property="scaryNumber" column="SCARY_NUMBER"/>
//        <result property="commentsNumber" column="COMMENTS_NUMBER"/>
    private int postWriter;
    private int postNo;
    private boolean postFavorite;
    private String postTitle;
    private String postCategory;
    private LocalDate postDate;
    private int scaryNumber;
    private int commentsNumber;

    public MyPagePostDTO() {}

    public MyPagePostDTO(int postWriter, int postNo, boolean postFavorite, String postTitle, String postCategory, LocalDate postDate, int scaryNumber, int commentsNumber) {
        this.postWriter = postWriter;
        this.postNo = postNo;
        this.postFavorite = postFavorite;
        this.postTitle = postTitle;
        this.postCategory = postCategory;
        this.postDate = postDate;
        this.scaryNumber = scaryNumber;
        this.commentsNumber = commentsNumber;
    }

    public int getPostWriter() {
        return postWriter;
    }

    public void setPostWriter(int postWriter) {
        this.postWriter = postWriter;
    }

    public int getPostNo() {
        return postNo;
    }

    public void setPostNo(int postNo) {
        this.postNo = postNo;
    }

    public boolean isPostFavorite() {
        return postFavorite;
    }

    public void setPostFavorite(boolean postFavorite) {
        this.postFavorite = postFavorite;
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

    public int getCommentsNumber() {
        return commentsNumber;
    }

    public void setCommentsNumber(int commentsNumber) {
        this.commentsNumber = commentsNumber;
    }

    @Override
    public String toString() {
        return "MyPagePostDTO{" +
                "postWriter=" + postWriter +
                ", postNo=" + postNo +
                ", postFavorite=" + postFavorite +
                ", postTitle='" + postTitle + '\'' +
                ", postCategory='" + postCategory + '\'' +
                ", postDate='" + postDate + '\'' +
                ", scaryNumber=" + scaryNumber +
                ", commentsNumber=" + commentsNumber +
                '}';
    }
}
