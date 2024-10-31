package com.ohgiraffers.mergyping.user.model.service;

import com.ohgiraffers.mergyping.user.model.dao.MyPageMapper;
import com.ohgiraffers.mergyping.user.model.dto.MyPageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyPageService {

    private final MyPageMapper mypageMapper;

    @Autowired
    public MyPageService(MyPageMapper mypageMapper) {
        this.mypageMapper = mypageMapper;
    }

    public MyPageDTO findNickName() {
        return mypageMapper.findNickName();
    }
}
