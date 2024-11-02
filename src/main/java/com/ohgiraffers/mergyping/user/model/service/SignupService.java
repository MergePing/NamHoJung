package com.ohgiraffers.mergyping.user.model.service;

import com.ohgiraffers.mergyping.user.model.dao.SignupMapper;
import com.ohgiraffers.mergyping.user.model.dto.LoginUserDTO;
import com.ohgiraffers.mergyping.user.model.dto.SignupDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class SignupService {

    @Autowired
    private SignupMapper signupMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public int regist(SignupDTO signupDTO) {
        signupDTO.setUserPass(passwordEncoder.encode(signupDTO.getUserPass()));

        int result = 0;

        try{
            result = signupMapper.regist(signupDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public LoginUserDTO findByUsername(String userId) {
        LoginUserDTO login = signupMapper.findByUsername(userId);

        if(Objects.isNull(login)) {
            return login;
        } else {
            return null;
        }
    }
}
