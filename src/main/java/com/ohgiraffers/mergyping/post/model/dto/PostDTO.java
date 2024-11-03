package com.ohgiraffers.mergyping.post.model.dto;

import java.util.Date;


public class PostDTO {
    private int postNo;
    private String postTitle;
    private String postCategory;
    private Date postDate;
    private int scaryNumber;
    private int commentNumber;
    private boolean postFavorite;

    private String formattedDate;
    public void setFormattedDate(String formattedDate) {
        this.formattedDate = formattedDate;
    }

    public String getFormattedDate() {
        return formattedDate;
    }


    public PostDTO() {}

    public PostDTO(int postNo, String postTitle, String postCategory, Date postDate, int scaryNumber, int commentNumber, boolean postFavorite) {
        this.postNo = postNo;
        this.postTitle = postTitle;
        this.postCategory = postCategory;
        this.postDate = postDate;
        this.scaryNumber = scaryNumber;
        this.commentNumber = commentNumber;
        this.postFavorite = postFavorite;
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

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
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

    public boolean getPostFavorite() {
        return postFavorite;
    }

    public void setPostFavorite(boolean postFavorite) {
        this.postFavorite = postFavorite;
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
                '}';
    }



}
