package com.ohgiraffers.mergyping.user.model.dto;

import java.sql.Blob;
import java.util.Date;

public class MyPageDTO {
//    <result property="userType" column="USER_TYPE"/>
//        <result property="userId" column="USER_ID"/>
//        <result property="userPwd" column="USER_PWD"/>
//        <result property="userName" column="USER_NAME"/>
//        <result property="email" column="EMAIL"/>
//        <result property="birthday" column="BIRTHDAY"/>
//        <result property="userImage" column="USER_IMAGE"/>
//        <result property="phone" column="PHONE"/>
//        <result property="rankNo" column="RANK_NO"/>
    private int userNo;
    private String userType;
    private String userId;
    private String userPwd;
    private String userName;
    private String email;
    private Date birthday;
    private Blob userImage;
    private String phone;
    private int levelNo;

    public MyPageDTO() {}

    public MyPageDTO(int userNo, String userType, String userId, String userPwd, String userName, String email, Date birthday, Blob userImage, String phone, int levelNo) {
        this.userNo = userNo;
        this.userType = userType;
        this.userId = userId;
        this.userPwd = userPwd;
        this.userName = userName;
        this.email = email;
        this.birthday = birthday;
        this.userImage = userImage;
        this.phone = phone;
        this.levelNo = levelNo;
    }

    public int getUserNo() {
        return userNo;
    }

    public void setUserNo(int userNo) {
        this.userNo = userNo;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
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

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Blob getUserImage() {
        return userImage;
    }

    public void setUserImage(Blob userImage) {
        this.userImage = userImage;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getLevelNo() {
        return levelNo;
    }

    public void setLevelNo(int levelNo) {
        this.levelNo = levelNo;
    }

    @Override
    public String toString() {
        return "MyPageDTO{" +
                "userNo=" + userNo +
                ", userType=" + userType +
                ", userId='" + userId + '\'' +
                ", userPwd='" + userPwd + '\'' +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", birthday=" + birthday +
                ", userImage=" + userImage +
                ", phone='" + phone + '\'' +
                ", levelNo=" + levelNo +
                '}';
    }
}



