package com.ohgiraffers.mergyping.user.model.dto;

import java.util.Date;

public class MainDTO {
    private String postTitle;
    private String postContent;

    public MainDTO() {}

    public MainDTO(String postTitle, String postContent) {
        this.postTitle = postTitle;
        this.postContent = postContent;
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
                "postTitle='" + postTitle + '\'' +
                ", postContent='" + postContent + '\'' +
                '}';
    }
}
