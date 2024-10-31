package com.ohgiraffers.mergyping.user.model.dto;

public class MyPageDTO {
    private int userNo;
    private String userName;


    public MyPageDTO() {}


    public MyPageDTO(int userNo, String userName) {
        this.userNo = userNo;
        this.userName = userName;
    }

    public int getUserNo() {
        return userNo;
    }

    public void setUserNo(int userNo) {
        this.userNo = userNo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "MyPageDTO{" +
                "userNo=" + userNo +
                ", userName='" + userName + '\'' +
                '}';
    }
}



