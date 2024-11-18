package com.ohgiraffers.mergyping.user.model.dto;

import java.util.Date;

public class MypageCommentDTO {
    private int commentNo;
    private String commentContent;
    private int userNo;
    private String commentDate;
    private int postNo;
    private int likeNumber;


    public MypageCommentDTO() {}

    public MypageCommentDTO(int commentNo, String commentContent, int userNo, String commentDate, int postNo, int likeNumber) {
        this.commentNo = commentNo;
        this.commentContent = commentContent;
        this.userNo = userNo;
        this.commentDate = commentDate;
        this.postNo = postNo;
        this.likeNumber = likeNumber;
    }

    public int getCommentNo() {
        return commentNo;
    }

    public void setCommentNo(int commentNo) {
        this.commentNo = commentNo;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public int getUserNo() {
        return userNo;
    }

    public void setUserNo(int userNo) {
        this.userNo = userNo;
    }

    public String getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(String commentDate) {
        this.commentDate = commentDate;
    }

    public int getPostNo() {
        return postNo;
    }

    public void setPostNo(int postNo) {
        this.postNo = postNo;
    }

    public int getLikeNumber() {
        return likeNumber;
    }

    public void setLikeNumber(int likeNumber) {
        this.likeNumber = likeNumber;
    }

    @Override
    public String toString() {
        return "MypageCommentDTO{" +
                "commentNo=" + commentNo +
                ", commentContent='" + commentContent + '\'' +
                ", userNo=" + userNo +
                ", commentDate='" + commentDate + '\'' +
                ", postNo=" + postNo +
                ", likeNumber=" + likeNumber +
                '}';
    }
}
