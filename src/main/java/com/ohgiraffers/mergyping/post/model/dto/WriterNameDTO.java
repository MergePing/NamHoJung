package com.ohgiraffers.mergyping.post.model.dto;

public class WriterNameDTO {
    private int userNo;
    private String level;
    private String userName;

    public WriterNameDTO() {}

    public WriterNameDTO(int userNo, String level, String userName) {
        this.userNo = userNo;
        this.level = level;
        this.userName = userName;
    }


    public int getUserNo() {
        return userNo;
    }

    public void setUserNo(int userNo) {
        this.userNo = userNo;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "WriterNameDTO{" +
                ", userNo=" + userNo +
                ", level='" + level + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }
}