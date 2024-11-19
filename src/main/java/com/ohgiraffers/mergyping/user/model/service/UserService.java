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

    public List<UserDTO> getUsersByPage(int page, int pageSize) {
        int offset = (page - 1) * pageSize;
        return userMapper.selectUsersByPage(offset, pageSize);
    }

    public int countUsers() {
        return userMapper.countAllUsers();
    }

    public UserDTO getUserById(int userId) {
        return userMapper.selectUserById(userId);
    }

    public boolean updateUserNickname(String userNo, String nickname) {
        return userMapper.updateNickname(userNo, nickname) > 0;
    }

    public void deleteUserByNo(String userNo, boolean isDeleted, String deleteDate) {
        userMapper.updateUserDeleteStatus(userNo, isDeleted, deleteDate);
    }

    // 닉네임으로 사용자 검색
    public List<UserDTO> searchUsersByNickname(String nickname) {
        return userMapper.searchUsersByNickname(nickname);
    }

    public void updateUserImage(String userId, String imagePath) {
        userMapper.updateUserImage(userId, imagePath);
    }
}