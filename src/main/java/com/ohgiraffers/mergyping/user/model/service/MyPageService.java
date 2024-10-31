package com.ohgiraffers.mergyping.user.model.service;

import com.ohgiraffers.mergyping.user.model.dao.MyPageMapper;
import org.springframework.stereotype.Service;

@Service
public class MyPageService {

    private final MyPageMapper mypageMapper;

    public MyPageService(MyPageMapper mypageMapper) {
        this.mypageMapper = mypageMapper;
    }

    public  String findNickName() {
        return mypageMapper.findNickName();
    }
}
