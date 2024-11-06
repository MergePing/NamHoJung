package com.ohgiraffers.mergyping.user.model.dto;

public class EmailDTO {

    private String email;
    private String authCode;

    public EmailDTO() {}

    public EmailDTO(String email, String authCode) {
        this.email = email;
        this.authCode = authCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    @Override
    public String toString() {
        return "EmailDTO{" +
                "email='" + email + '\'' +
                ", authCode='" + authCode + '\'' +
                '}';
    }
}
