package com.ohgiraffers.mergyping.comment.model.dto;

import java.time.LocalDate;

public class CommentDTO {
    private int commentNo;
    private String commentContent;
    private int userNo;
    private LocalDate commentDate;
    private String username;
    private String userLevel;
    private int postNo;
    private int top;
    private int index;

    public CommentDTO() {}

    public CommentDTO(int commentNo, String commentContent, int userNo, LocalDate commentDate, String username, String userLevel, int postNo, int top, int index) {
        this.commentNo = commentNo;
        this.commentContent = commentContent;
        this.userNo = userNo;
        this.commentDate = commentDate;
        this.username = username;
        this.userLevel = userLevel;
        this.postNo = postNo;
        this.top = top;
        this.index = index;
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

    public LocalDate getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(LocalDate commentDate) {
        this.commentDate = commentDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(String userLevel) {
        this.userLevel = userLevel;
    }

    public int getPostNo() {
        return postNo;
    }

    public void setPostNo(int postNo) {
        this.postNo = postNo;
    }

    public int getTop() {
        return top;
    }

    public void setTop(int top) {
        this.top = top;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public String toString() {
        return "CommentDTO{" +
                "commentNo=" + commentNo +
                ", commentContent='" + commentContent + '\'' +
                ", userNo=" + userNo +
                ", commentDate=" + commentDate +
                ", username='" + username + '\'' +
                ", userLevel='" + userLevel + '\'' +
                ", postNo=" + postNo +
                ", top=" + top +
                ", index=" + index +
                '}';
    }
}
