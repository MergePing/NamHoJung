package com.ohgiraffers.mergyping.mbti.model.dto;

public class QuestionDTO {
    private int No;
    private String image;
    private String text;

    public QuestionDTO() {}

    public QuestionDTO(int no, String image, String text) {
        No = no;
        this.image = image;
        this.text = text;
    }

    public int getNo() {
        return No;
    }

    public void setNo(int no) {
        No = no;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "QuestionDTO{" +
                "No=" + No +
                ", image='" + image + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
