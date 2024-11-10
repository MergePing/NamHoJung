package com.ohgiraffers.mergyping.auth.model.service;

import com.ohgiraffers.mergyping.auth.model.AuthDetails;
import com.ohgiraffers.mergyping.user.model.dto.LoginUserDTO;
import com.ohgiraffers.mergyping.user.model.service.SignupService;
import com.ohgiraffers.mergyping.user.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    private SignupService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        LoginUserDTO login =userService.findByUsername(username);

        if(Objects.isNull(login)) {
            throw new UsernameNotFoundException("해당하는 회원 정보가 존재하지 않습니다.");
        }
        System.out.println(login);
        return new AuthDetails(login);
    }

        public LoginUserDTO authenticate(String username, String password) {
            // username으로 사용자 정보를 조회
            LoginUserDTO user = userService.findByUsername(username);

            // 사용자가 존재하지 않거나 패스워드가 일치하지 않는 경우
            if (user == null || !user.getUserPass().equals(password)) {
                return null; // 또는 예외를 던질 수 있습니다.
            }

            // 사용자 정보가 일치하는 경우 반환
            return user;
        }
    }



