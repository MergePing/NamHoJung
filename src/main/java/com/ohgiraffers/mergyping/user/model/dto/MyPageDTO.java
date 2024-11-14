package com.ohgiraffers.mergyping.user.model.dto;

public class MyPageDTO {
    private String userName;
    private String email;
    private int userNo;
    private String userId;
    private String userPwd;
    private String profileImage;
    private String level;
    private String content;
    private String title;
    private String imgFirst;
    private String imgSecond;

    public MyPageDTO(){}

    public MyPageDTO(String userName, String email, int userNo, String userId, String userPwd, String profileImage, String level, String content, String title, String imgFirst, String imgSecond) {
        this.userName = userName;
        this.email = email;
        this.userNo = userNo;
        this.userId = userId;
        this.userPwd = userPwd;
        this.profileImage = profileImage;
        this.level = level;
        this.content = content;
        this.title = title;
        this.imgFirst = imgFirst;
        this.imgSecond = imgSecond;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getUserNo() {
        return userNo;
    }

    public void setUserNo(int userNo) {
        this.userNo = userNo;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgFirst() {
        return imgFirst;
    }

    public void setImgFirst(String imgFirst) {
        this.imgFirst = imgFirst;
    }

    public String getImgSecond() {
        return imgSecond;
    }

    public void setImgSecond(String imgSecond) {
        this.imgSecond = imgSecond;
    }

    @Override
    public String toString() {
        return "MyPageDTO{" +
                "userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", userNo=" + userNo +
                ", userId='" + userId + '\'' +
                ", userPwd='" + userPwd + '\'' +
                ", profileImage='" + profileImage + '\'' +
                ", level='" + level + '\'' +
                ", content='" + content + '\'' +
                ", title='" + title + '\'' +
                ", imgFirst='" + imgFirst + '\'' +
                ", imgSecond='" + imgSecond + '\'' +
                '}';
    }
}