package com.ohgiraffers.mergyping.post.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public class SelectPostDTO {
    private int postNo;
    private String postTitle;
    private String postCategory;
    private int postWriter;
    private String postWriterName;
    private String postContent;
    private LocalDate postDate;
    private int scaryNumber;
    private int notScaryNumber;
    private int commentNumber;
    private int postReport;
    private String postImageFirst;
    private String postImageSecond;
    private boolean postFavorite;
    private boolean scary;
    private boolean notScary;
    private String postImageFirstExtension;
    private String postImageSecondExtension;
    private String profileImage;
    private String userProfileImage;

    public SelectPostDTO() {}

    public SelectPostDTO(int postNo, String postTitle, String postCategory, int postWriter, String postWriterName, String postContent, LocalDate postDate, int scaryNumber, int notScaryNumber, int commentNumber, int postReport, String postImageFirst, String postImageSecond, boolean postFavorite, boolean scary, boolean notScary, String postImageFirstExtension, String postImageSecondExtension, String profileImage, String userProfileImage) {
        this.postNo = postNo;
        this.postTitle = postTitle;
        this.postCategory = postCategory;
        this.postWriter = postWriter;
        this.postWriterName = postWriterName;
        this.postContent = postContent;
        this.postDate = postDate;
        this.scaryNumber = scaryNumber;
        this.notScaryNumber = notScaryNumber;
        this.commentNumber = commentNumber;
        this.postReport = postReport;
        this.postImageFirst = postImageFirst;
        this.postImageSecond = postImageSecond;
        this.postFavorite = postFavorite;
        this.scary = scary;
        this.notScary = notScary;
        this.postImageFirstExtension = postImageFirstExtension;
        this.postImageSecondExtension = postImageSecondExtension;
        this.profileImage = profileImage;
        this.userProfileImage = userProfileImage;
    }

    public String getUserProfileImage() {
        return userProfileImage;
    }

    public void setUserProfileImage(String userProfileImage) {
        this.userProfileImage = userProfileImage;
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

    public int getPostWriter() {
        return postWriter;
    }

    public void setPostWriter(int postWriter) {
        this.postWriter = postWriter;
    }

    public String getPostWriterName() {
        return postWriterName;
    }

    public void setPostWriterName(String postWriterName) {
        this.postWriterName = postWriterName;
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

    public int getNotScaryNumber() {
        return notScaryNumber;
    }

    public void setNotScaryNumber(int notScaryNumber) {
        this.notScaryNumber = notScaryNumber;
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

    public String getPostImageFirst() {
        return postImageFirst;
    }

    public void setPostImageFirst(String postImageFirst) {
        this.postImageFirst = postImageFirst;
    }

    public String getPostImageSecond() {
        return postImageSecond;
    }

    public void setPostImageSecond(String postImageSecond) {
        this.postImageSecond = postImageSecond;
    }

    public boolean isPostFavorite() {
        return postFavorite;
    }

    public void setPostFavorite(boolean postFavorite) {
        this.postFavorite = postFavorite;
    }

    public boolean isScary() {
        return scary;
    }

    public void setScary(boolean scary) {
        this.scary = scary;
    }

    public boolean isNotScary() {
        return notScary;
    }

    public void setNotScary(boolean notScary) {
        this.notScary = notScary;
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

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    @Override
    public String toString() {
        return "SelectPostDTO{" +
                "postNo=" + postNo +
                ", postTitle='" + postTitle + '\'' +
                ", postCategory='" + postCategory + '\'' +
                ", postWriter=" + postWriter +
                ", postWriterName='" + postWriterName + '\'' +
                ", postContent='" + postContent + '\'' +
                ", postDate=" + postDate +
                ", scaryNumber=" + scaryNumber +
                ", notScaryNumber=" + notScaryNumber +
                ", commentNumber=" + commentNumber +
                ", postReport=" + postReport +
                ", postImageFirst='" + postImageFirst + '\'' +
                ", postImageSecond='" + postImageSecond + '\'' +
                ", postFavorite=" + postFavorite +
                ", scary=" + scary +
                ", notScary=" + notScary +
                ", postImageFirstExtension='" + postImageFirstExtension + '\'' +
                ", postImageSecondExtension='" + postImageSecondExtension + '\'' +
                ", profileImage='" + profileImage + '\'' +
                ", userProfileImage='" + userProfileImage + '\'' +
                '}';
    }
}