package com.ohgiraffers.mergyping.user.model.dao;

import com.ohgiraffers.mergyping.user.model.dto.LoginUserDTO;
import com.ohgiraffers.mergyping.user.model.dto.SignupDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SignupMapper {
    int regist(SignupDTO signupDTO);

    LoginUserDTO findByUsername(String userId);

    int checkId(String userId);
}