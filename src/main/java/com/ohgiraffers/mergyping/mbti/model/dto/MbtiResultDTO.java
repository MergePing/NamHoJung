package com.ohgiraffers.mergyping.mbti.model.dto;

public class MbtiResultDTO {
    private int UserNo;
    private String mbtiType;
    private boolean mbtiStatus;

    public MbtiResultDTO() {
        this.mbtiStatus=true;
    }

    public MbtiResultDTO(int userNo, String mbtiType, boolean mbtiStatus) {
        UserNo = userNo;
        this.mbtiType = mbtiType;
        this.mbtiStatus = mbtiStatus;
    }

    public int getUserNo() {
        return UserNo;
    }

    public void setUserNo(int userNo) {
        UserNo = userNo;
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
        return "MbtiResultDTO{" +
                "UserNo=" + UserNo +
                ", mbtiType='" + mbtiType + '\'' +
                ", mbtiStatus=" + mbtiStatus +
                '}';
    }
}
