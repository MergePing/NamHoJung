package com.ohgiraffers.mergyping.mbti.model.dto;

public class MbtiDbDTO {
    private String type;
    private String result;
    private String info;

    public MbtiDbDTO() {}

    public MbtiDbDTO(String type, String result, String info) {
        this.type = type;
        this.result = result;
        this.info = info;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
        return "MbtiDbDTO{" +
                "type='" + type + '\'' +
                ", result='" + result + '\'' +
                ", info='" + info + '\'' +
                '}';
    }
}
