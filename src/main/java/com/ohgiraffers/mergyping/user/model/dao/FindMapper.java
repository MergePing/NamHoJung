package com.ohgiraffers.mergyping.user.model.dao;

import com.ohgiraffers.mergyping.user.model.dto.FIndUserDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FindMapper {

    FIndUserDTO findIdByEmail(String email);
}
