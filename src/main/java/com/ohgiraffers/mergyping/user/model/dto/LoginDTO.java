package com.ohgiraffers.mergyping.user.model.dto;

import com.ohgiraffers.mergyping.common.UserRole;

public class LoginDTO {
    private int userNo;
    private String userId;
    private String userPass;
    private UserRole userRole;
    private String userName;
    private String userImage;
    private boolean success;

    public LoginDTO() {}

    public LoginDTO(int userNo, String userId, String userPass, UserRole userRole, String userName, String userImage, boolean success) {
        this.userNo = userNo;
        this.userId = userId;
        this.userPass = userPass;
        this.userRole = userRole;
        this.userName = userName;
        this.userImage = userImage;
        this.success = success;
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

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    @Override
    public String toString() {
        return "LoginDTO{" +
                "userNo=" + userNo +
                ", userId='" + userId + '\'' +
                ", userPass='" + userPass + '\'' +
                ", userRole=" + userRole +
                ", userName='" + userName + '\'' +
                ", userImage='" + userImage + '\'' +
                ", success=" + success +
                '}';
    }
}
