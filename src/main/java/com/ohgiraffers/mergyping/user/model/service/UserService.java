package com.ohgiraffers.mergyping.user.model.service;

import com.ohgiraffers.mergyping.user.model.dao.UserMapper;
import com.ohgiraffers.mergyping.user.model.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public UserDTO getUserById(int userId) {
        return userMapper.selectUserById(userId);
    }


    @Transactional
    public boolean deleteUserByNo(int userNo) {
        // 액션 삭제
        userMapper.deleteActionsByUserNo(userNo);

        // 댓글 삭제
        userMapper.deleteCommentsByUserNo(userNo);

        // 게시물 삭제
        userMapper.deletePostsByUserNo(userNo);

        // 유저 삭제
        int result = userMapper.deleteUser(userNo);

        return result > 0;
    }

}
