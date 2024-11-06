package com.ohgiraffers.mergyping.user.model.dao;

import com.ohgiraffers.mergyping.user.model.dto.MyPageDTO;
import com.ohgiraffers.mergyping.user.model.dto.MyPagePostDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface MyPageMapper {
    MyPageDTO findNickName(int userNo);

    MyPageDTO findEmail(int userNo);

    boolean existsByNickname(String nickname);

    void modifyUserName(MyPageDTO myPageDTO);

    List<MyPagePostDTO> findWrittenPost(int userNo);

    void updatePassword(Map<String,Object> params);


    MyPageDTO findId(int userNo);
}
