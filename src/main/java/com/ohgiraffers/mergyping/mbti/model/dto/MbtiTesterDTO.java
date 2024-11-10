package com.ohgiraffers.mergyping.mbti.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;

@Component
public class MbtiTesterDTO {
    @JsonProperty("CB")
    private int CB;
    @JsonProperty("HG")
    private int HG;
    @JsonProperty("SE")
    private int SE;
    @JsonProperty("TM")
    private int TM;

    public MbtiTesterDTO() {}

    public MbtiTesterDTO(int CB, int HG, int SE, int TM) {
        this.CB = CB;
        this.HG = HG;
        this.SE = SE;
        this.TM = TM;
    }

    public int getCB() {
        return CB;
    }

    public void setCB(int CB) {
        this.CB = CB;
    }

    public int getHG() {
        return HG;
    }

    public void setHG(int HG) {
        this.HG = HG;
    }

    public int getSE() {
        return SE;
    }

    public void setSE(int SE) {
        this.SE = SE;
    }

    public int getTM() {
        return TM;
    }

    public void setTM(int TM) {
        this.TM = TM;
    }

    @Override
    public String toString() {
        return "MbtiTester{" +
                "CB=" + CB +
                ", HG=" + HG +
                ", SE=" + SE +
                ", TM=" + TM +
                '}';
    }

    // 이건 혁신이다. 알수록 무궁무진한 코딩의 세계
    public void updateMbti(int questionNo, String answer) {
        if (questionNo >= 1 && questionNo <= 9) { // CB 카테고리 (1~9번)
            if ("yes".equals(answer)) {
                CB++; // 예일 때 증가
            } else if ("no".equals(answer)) {
                CB--; // 아니오일 때 감소
            }
        } else if (questionNo >= 10 && questionNo <= 18) { // HG 카테고리 (10~18번)
            if ("yes".equals(answer)) {
                HG++;
            } else if ("no".equals(answer)) {
                HG--;
            }
        } else if (questionNo >= 19 && questionNo <= 27) { // SE 카테고리 (19~27번)
            if ("yes".equals(answer)) {
                SE++;
            } else if ("no".equals(answer)) {
                SE--;
            }
        } else if (questionNo >= 28 && questionNo <= 36) { // TM 카테고리 (28~36번)
            if ("yes".equals(answer)) {
                TM++;
            } else if ("no".equals(answer)) {
                TM--;
            }
        }
    }
    
}
