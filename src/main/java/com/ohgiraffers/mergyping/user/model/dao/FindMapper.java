package com.ohgiraffers.mergyping.user.model.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface FindMapper {

    FindUserDTO findIdByEmail(String userEmail);

    int changePwd(FindUserDTO findUserDTO);

    Optional<FindUserDTO> findPwdByEmail(String email);

    void savePwd(FindUserDTO findUser);
}
