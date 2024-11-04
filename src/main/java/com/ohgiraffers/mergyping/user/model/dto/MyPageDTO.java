package com.ohgiraffers.mergyping.user.model.dto;

public class MyPageDTO {
    private String userName;
    private String email;
    private int userNo;

    public MyPageDTO(){}

    public MyPageDTO(String userName, String email, int userNo) {

        this.userName = userName;
        this.email = email;
        this.userNo = userNo;
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

    @Override
    public String toString() {
        return "MyPageDTO{" +
                "userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", userNo=" + userNo +
                '}';
    }
}
