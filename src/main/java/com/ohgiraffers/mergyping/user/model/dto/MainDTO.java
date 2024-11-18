package com.ohgiraffers.mergyping.user.model.dto;

import java.util.Date;

public class MainDTO {
    private int postNumber;
    private String postTitle;
    private String postContent;

    public MainDTO() {}

    public MainDTO(int postNumber, String postTitle, String postContent) {
        this.postNumber = postNumber;
        this.postTitle = postTitle;
        this.postContent = postContent;
    }

    public int getPostNumber() {
        return postNumber;
    }

    public void setPostNumber(int postNumber) {
        this.postNumber = postNumber;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    @Override
    public String toString() {
        return "MainDTO{" +
                "postNumber=" + postNumber +
                ", postTitle='" + postTitle + '\'' +
                ", postContent='" + postContent + '\'' +
                '}';
    }
}
