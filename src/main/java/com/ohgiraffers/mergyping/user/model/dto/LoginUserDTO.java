package com.ohgiraffers.mergyping.user.model.dto;

import com.ohgiraffers.mergyping.common.UserRole;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LoginUserDTO {
    private int userNo;
    private String userId;
    private String userPass;
    private UserRole userRole;
    private String userName;
    private String userImage;


    public List<String> getRole() {
        if(this.userRole.getRole().length() > 0) {
            return Arrays.asList(this.userRole.getRole().split(","));
        }
        return new ArrayList<>();
    }
    public LoginUserDTO() {}

    public LoginUserDTO(int userNo, String userId, String userPass, UserRole userRole, String userName, String userImage) {
        this.userNo = userNo;
        this.userId = userId;
        this.userPass = userPass;
        this.userRole = userRole;
        this.userName = userName;
        this.userImage = userImage;
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

    public void setUserImage(String fileUrl) {
    }

    @Override
    public String toString() {
        return "LoginUserDTO{" +
                "userNo=" + userNo +
                ", userId='" + userId + '\'' +
                ", userPass='" + userPass + '\'' +
                ", userRole=" + userRole +
                ", userName='" + userName + '\'' +
                ", userImage='" + userImage + '\'' +
                '}';
    }
}