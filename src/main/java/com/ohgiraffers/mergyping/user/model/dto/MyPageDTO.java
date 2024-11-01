package com.ohgiraffers.mergyping.user.model.dto;

public class MyPageDTO {
    private String userName;

    public MyPageDTO(){}

    public MyPageDTO(String userName) {
        this.userName = userName;
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
                "userName='" + userName + '\'' +
                '}';
    }
}
