package com.ohgiraffers.mergyping.mbti.model.dto;

public class MbtiResultDTO {
    private int userNo;
    private String mbtiType;
    private boolean mbtiStatus;

    public MbtiResultDTO() {
        this.mbtiStatus=true;
    }

    public MbtiResultDTO(int userNo, String mbtiType, boolean mbtiStatus) {
        this.userNo = userNo;
        this.mbtiType = mbtiType;
        this.mbtiStatus = mbtiStatus;
    }

    public int getUserNo() {
        return userNo;
    }

    public void setUserNo(int userNo) {
        this.userNo = userNo;
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
                "userNo=" + userNo +
                ", mbtiType='" + mbtiType + '\'' +
                ", mbtiStatus=" + mbtiStatus +
                '}';
    }
}
