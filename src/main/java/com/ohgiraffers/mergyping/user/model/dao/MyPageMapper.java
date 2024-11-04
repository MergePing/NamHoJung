package com.ohgiraffers.mergyping.user.model.dao;

import com.ohgiraffers.mergyping.user.model.dto.MyPageDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MyPageMapper {
    MyPageDTO findNickName();

    MyPageDTO findEmail();

    boolean existsByNickname(String nickname);

    void modifyUserName(MyPageDTO myPageDTO);
}
