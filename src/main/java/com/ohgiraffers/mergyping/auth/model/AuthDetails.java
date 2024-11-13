package com.ohgiraffers.mergyping.auth.model;

import com.ohgiraffers.mergyping.common.UserRole;
import com.ohgiraffers.mergyping.user.model.dto.LoginUserDTO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class AuthDetails implements UserDetails {

    private LoginUserDTO loginUserDTO;

    public AuthDetails() {}

    public AuthDetails(LoginUserDTO LoginUserDTO) {
        this.loginUserDTO = LoginUserDTO;
    }

    public LoginUserDTO getLoginUserDTO() {
        return loginUserDTO;
    }

    public void setLoginUserDTO(LoginUserDTO loginUserDTO) {
        this.loginUserDTO = loginUserDTO;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();

        loginUserDTO.getRole().forEach(role -> authorities.add(() -> role));
        return authorities;
    }

    @Override
    public String getPassword() {

        return loginUserDTO.getUserPass();
    }

    @Override
    public String getUsername() {
        return loginUserDTO.getUserId();
    }


    public int getUserNo(){
        return loginUserDTO.getUserNo();
    }

    public UserRole getUserRole(){
        return loginUserDTO.getUserRole();
    }


    // 전부 DB에 컬럼 있어야 사용 가능 -> 현재 탈퇴 날짜가 있어 사용가능함
    // 계정 만료용? 탈퇴하고 ? 넣으면 될듯함 -> 만료된 계정 false
    @Override
    public boolean isAccountNonExpired() {
//        return UserDetails.super.isAccountNonExpired();
        return true;
    }

    // 계정 잠금 확인용 -> 잠금 : false , 열림 true
    @Override
    public boolean isAccountNonLocked() {
//        return UserDetails.super.isAccountNonLocked();
        return true;
    }

    // 비밀번호 만료 -> false 반환 만료 -> 보안을 위해 비밀번호 만료날짜?
    @Override
    public boolean isCredentialsNonExpired() {
//        return UserDetails.super.isCredentialsNonExpired();
        return true;
    }

    // 계정 활성화 확인용 -> true 활성화, false 비활성화
    @Override
    public boolean isEnabled() {
//        return UserDetails.super.isEnabled();
        return true;
    }
}
