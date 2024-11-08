package com.ohgiraffers.mergyping.user.model.service;

import com.ohgiraffers.mergyping.user.model.dao.SignupMapper;
import com.ohgiraffers.mergyping.user.model.dto.LoginUserDTO;
import com.ohgiraffers.mergyping.user.model.dto.SignupDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
public class SignupService {

    private final SignupMapper signupMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Autowired
    public SignupService(SignupMapper signupMapper) {
        this.signupMapper = signupMapper;
    }

    @Transactional
    public int regist(SignupDTO signupDTO) {
        signupDTO.setUserPass(passwordEncoder.encode(signupDTO.getUserPass()));
        signupMapper.regist(signupDTO);
        signupMapper.registMbti(signupDTO);
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

        if(!Objects.isNull(login)) {
            return login;
        } else {
            return null;
        }
    }

    public boolean checkId(String userId) {
        return signupMapper.checkId(userId) == 0;
    }

    public boolean checkNick(String userNick) {
        return signupMapper.checkNick(userNick) == 0;
    }

    public Boolean checkEmailAvailability(String userEmail) {
        return signupMapper.checkEmail(userEmail) == null;
    }
}
