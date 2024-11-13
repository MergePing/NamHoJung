package com.ohgiraffers.mergyping.user.model.service;

import com.ohgiraffers.mergyping.user.model.dao.FindMapper;
import com.ohgiraffers.mergyping.user.model.dto.FindUserDTO;
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

    public Optional<FindUserDTO> findId(String userEmail) {
        FindUserDTO findUserDTO = findMapper.findIdByEmail(userEmail);
        if(findUserDTO != null) {
            return Optional.of(findUserDTO);
        }
        return Optional.empty();
    };


}
