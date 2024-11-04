package com.ohgiraffers.mergyping.user.model.service;

import com.ohgiraffers.mergyping.user.model.dao.MyPageMapper;
import com.ohgiraffers.mergyping.user.model.dto.MyPageDTO;
import com.ohgiraffers.mergyping.user.model.dto.MyPagePostDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    @Transactional
    public void modifyUserName(MyPageDTO myPageDTO) {
        myPageMapper.modifyUserName(myPageDTO);
    }

    public List<MyPagePostDTO> findWrittenPost() {

        return myPageMapper.findWrittenPost();
    }
}
