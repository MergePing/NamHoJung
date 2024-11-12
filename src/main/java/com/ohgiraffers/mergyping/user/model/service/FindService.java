package com.ohgiraffers.mergyping.user.model.service;

import com.ohgiraffers.mergyping.user.model.dao.FindMapper;
import com.ohgiraffers.mergyping.user.model.dao.UserMapper;
import com.ohgiraffers.mergyping.user.model.dto.FIndUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FindService {

    private final FindMapper findMapper;

    @Autowired
    public FindService(FindMapper findMapper) {
        this.findMapper = findMapper;
    }

    public Optional<FIndUserDTO> findId(String userEmail) {
        FIndUserDTO fIndUserDTO = findMapper.findIdByEmail(userEmail);
        if(fIndUserDTO != null) {
            return Optional.of(fIndUserDTO);
        }
        return Optional.empty();
    };


}
