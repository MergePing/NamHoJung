package com.ohgiraffers.mergyping.mbti.model.dto;

public class MbtiInfoDTO {
    private int no;
    private String type;
    private boolean status;
    private String result;
    private String info;

    public MbtiInfoDTO() {
    }

    public MbtiInfoDTO(int no, String type, boolean status, String result, String info) {
        this.no = no;
        this.type = type;
        this.status = status;
        this.result = result;
        this.info = info;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "MbtiInfoDTO{" +
                "no=" + no +
                ", type='" + type + '\'' +
                ", status=" + status +
                ", result='" + result + '\'' +
                ", info='" + info + '\'' +
                '}';
    }
}