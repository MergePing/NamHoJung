package com.ohgiraffers.mergyping.user.model.dto;

import com.ohgiraffers.mergyping.common.UserRole;

import java.sql.Date;

public class SignupDTO {
    private String userId;
    private String userPass;
    private String userName;
    private String userEmail;
    private Date signupDate;
    private UserRole userRole;

    public SignupDTO() {}

    public SignupDTO(String userId, String userPass, String userName, String userEmail, Date signupDate, UserRole userRole) {
        this.userId = userId;
        this.userPass = userPass;
        this.userName = userName;
        this.userEmail = userEmail;
        this.signupDate = signupDate;
        this.userRole = userRole;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Date getSignupDate() {
        return signupDate;
    }

    public void setSignupDate(Date signupDate) {
        this.signupDate = signupDate;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    @Override
    public String toString() {
        return "SignupDTO{" +
                "userId='" + userId + '\'' +
                ", userPass='" + userPass + '\'' +
                ", userName='" + userName + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", signupDate=" + signupDate +
                ", userRole=" + userRole +
                '}';
    }
}


