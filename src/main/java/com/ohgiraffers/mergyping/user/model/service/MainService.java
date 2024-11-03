package com.ohgiraffers.mergyping.user.model.service;

import com.ohgiraffers.mergyping.user.model.dao.MainMapper;
import com.ohgiraffers.mergyping.user.model.dto.MainDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MainService {

    private final MainMapper mainMapper;

    @Autowired
    public MainService(MainMapper mainMapper){
        this.mainMapper=mainMapper;
    }

    public List<MainDTO> bestPost(){
        return mainMapper.bestPost();
    }

}
