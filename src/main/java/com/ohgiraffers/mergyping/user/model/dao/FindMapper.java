package com.ohgiraffers.mergyping.user.model.dao;

import com.ohgiraffers.mergyping.user.model.dto.FindUserDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FindMapper {

    FindUserDTO findIdByEmail(String userEmail);
}
