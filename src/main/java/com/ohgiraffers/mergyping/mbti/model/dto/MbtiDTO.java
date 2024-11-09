package com.ohgiraffers.mergyping.mbti.model.dto;

public class MbtiDTO {
    private int questionNo;
    private String questionImage;
    private String question;

    public MbtiDTO() {}

    public MbtiDTO(int questionNo, String questionImage, String question) {
        this.questionNo = questionNo;
        this.questionImage = questionImage;
        this.question = question;
    }

    public int getQuestionNo() {
        return questionNo;
    }

    public void setQuestionNo(int questionNo) {
        this.questionNo = questionNo;
    }

    public String getQuestionImage() {
        return questionImage;
    }

    public void setQuestionImage(String questionImage) {
        this.questionImage = questionImage;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    @Override
    public String toString() {
        return "MbtiDTO{" +
                "questionNo=" + questionNo +
                ", questionImage='" + questionImage + '\'' +
                ", question='" + question + '\'' +
                '}';
    }
}
