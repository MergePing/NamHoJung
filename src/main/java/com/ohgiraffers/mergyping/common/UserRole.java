package com.ohgiraffers.mergyping.common;

public enum UserRole {

    USER("USER"),
    ADMIN("ADMIN");

    //위 초기화 안해서 나는 에러 해결을 위해 초기화
    private String role;

    UserRole(String role) {
        this.role=role;
    }

    public String getRole() {
        return role;
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "role='" + role + '\'' +
                '}';
    }

}
