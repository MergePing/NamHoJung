package com.ohgiraffers.mergyping.user.model.dao;

import com.ohgiraffers.mergyping.user.model.dto.LoginUserDTO;
import com.ohgiraffers.mergyping.user.model.dto.SignupDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SignupMapper {
    int regist(SignupDTO signupDTO);

    int registMbti(SignupDTO signupDTO);

    LoginUserDTO findByUsername(String userId);

    int checkId(String userId);

    int checkNick(String userNick);

    Boolean checkEmail(String userEmail);

    LoginUserDTO login(String userId);
}
