package com.ohgiraffers.mergyping.user.model.dto;

public class MyPageDTO {
    private String userName;
    private String email;
    private int userNo;
    private String userId;
    private String userPwd;
    private String profileImage;

    public MyPageDTO(){}

    public MyPageDTO(String userName, String email, int userNo, String userId, String userPwd, String profileImage) {
        this.userName = userName;
        this.email = email;
        this.userNo = userNo;
        this.userId = userId;
        this.userPwd = userPwd;
        this.profileImage = profileImage;
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

    @Override
    public String toString() {
        return "MyPageDTO{" +
                "userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", userNo=" + userNo +
                ", userId='" + userId + '\'' +
                ", userPwd='" + userPwd + '\'' +
                ", profileImage='" + profileImage + '\'' +
                '}';
    }
}