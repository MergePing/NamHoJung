package com.ohgiraffers.mergyping.user.model.dto;

import java.util.Date;

public class UserDTO {
    private String userNo;
    private String userId;
    private String level;
    private String userName;
    private int reportCount;
    private boolean isDeleted;

    public UserDTO() {}

    public UserDTO(String userNo, String userId, String level, String userName, int reportCount, boolean isDeleted) {
        this.userNo = userNo;
        this.userId = userId;
        this.level = level;
        this.userName = userName;
        this.reportCount = reportCount;
        this.isDeleted = isDeleted;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public int getReportCount() {
        return reportCount;
    }

    public void setReportCount(int reportCount) {
        this.reportCount = reportCount;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
