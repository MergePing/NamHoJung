package com.ohgiraffers.mergyping.user.model.service;

import com.ohgiraffers.mergyping.user.model.dao.MyPageMapper;
import com.ohgiraffers.mergyping.user.model.dto.MyPageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyPageService {

    private  final MyPageMapper myPageMapper;
    @Autowired
    public MyPageService(MyPageMapper myPageMapper) {
        this.myPageMapper = myPageMapper;
    }

    public MyPageDTO findNickName() {
        return myPageMapper.findNickName();
    }

    public MyPageDTO findEmail() {
        return myPageMapper.findEmail();
    }


    public boolean isNicknameDuplicate(String nickname) {
        return myPageMapper.existsByNickname(nickname);
    }
}
