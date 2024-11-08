package com.ohgiraffers.mergyping.user.model.dto;

import com.ohgiraffers.mergyping.common.Gender;
import com.ohgiraffers.mergyping.common.UserRole;

import java.time.LocalDate;

public class SignupDTO {
    private int userNo;
    private String userId;
    private String userPass;
    private String userName;
    private String userEmail;
    private LocalDate userBirth;
    private Gender userGender;
    private UserRole userRole;
    private String mbtiType = "NONE";
    private boolean mbtiStatus = false;

    public SignupDTO() {}

    public SignupDTO(int userNo, String userId, String userPass, String userName, String userEmail, LocalDate userBirth, Gender userGender, UserRole userRole, String mbtiType, boolean mbtiStatus) {
        this.userNo = userNo;
        this.userId = userId;
        this.userPass = userPass;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userBirth = userBirth;
        this.userGender = userGender;
        this.userRole = userRole;
        this.mbtiType = mbtiType;
        this.mbtiStatus = mbtiStatus;
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

    public LocalDate getUserBirth() {
        return userBirth;
    }

    public void setUserBirth(LocalDate userBirth) {
        this.userBirth = userBirth;
    }

    public Gender getUserGender() {
        return userGender;
    }

    public void setUserGender(Gender userGender) {
        this.userGender = userGender;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public String getMbtiType() {
        return mbtiType;
    }

    public void setMbtiType(String mbtiType) {
        this.mbtiType = mbtiType;
    }

    public boolean isMbtiStatus() {
        return mbtiStatus;
    }

    public void setMbtiStatus(boolean mbtiStatus) {
        this.mbtiStatus = mbtiStatus;
    }

    @Override
    public String toString() {
        return "SignupDTO{" +
                "userNo=" + userNo +
                ", userId='" + userId + '\'' +
                ", userPass='" + userPass + '\'' +
                ", userName='" + userName + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userBirth=" + userBirth +
                ", userGender=" + userGender +
                ", userRole=" + userRole +
                ", mbtiType='" + mbtiType + '\'' +
                ", mbtiStatus=" + mbtiStatus +
                '}';
    }
}

