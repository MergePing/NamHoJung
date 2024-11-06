package com.ohgiraffers.mergyping.user.model.dto;

public class EmailMessageDTO {
    private boolean success;
    private String message;

    public EmailMessageDTO(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "EmailMessageDTO{" +
                "success=" + success +
                ", message='" + message + '\'' +
                '}';
    }
}
