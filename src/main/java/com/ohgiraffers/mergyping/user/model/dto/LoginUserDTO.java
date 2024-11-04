package com.ohgiraffers.mergyping.user.model.dto;

import com.ohgiraffers.mergyping.common.UserRole;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LoginUserDTO {
    private String userId;
    private String userPass;
    private UserRole userRole;

    public LoginUserDTO(String userId, String userPass, UserRole userRole) {
        this.userId = userId;
        this.userPass = userPass;
        this.userRole = userRole;
    }

    public List<String> getRole() {
        if(this.userRole.getRole().length() > 0) {
            return Arrays.asList(this.userRole.getRole().split(","));
        }
        return new ArrayList<>();
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

    @Override
    public String toString() {
        return "LoginUserDTO{" +
                "userId='" + userId + '\'' +
                ", userPass='" + userPass + '\'' +
                ", userRole=" + userRole +
                '}';
    }
}
