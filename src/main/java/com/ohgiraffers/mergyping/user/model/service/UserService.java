package com.ohgiraffers.mergyping.user.model.service;

import com.ohgiraffers.mergyping.user.model.dao.UserMapper;
import com.ohgiraffers.mergyping.user.model.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserMapper userMapper;

    @Autowired
    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public List<UserDTO> getAllUsers() {
        return userMapper.selectAllUsers();
    }
    // UserService.java
    public List<UserDTO> getUsersByPage(int page, int pageSize) {
        int offset = (page - 1) * pageSize;
        return userMapper.selectUsersByPage(offset, pageSize);
    }

    public int countUsers() {
        return userMapper.countAllUsers();
    }
 }
