package com.ohgiraffers.mergyping.user.model.service;

import com.ohgiraffers.mergyping.user.model.dao.FindMapper;
import com.ohgiraffers.mergyping.user.model.dto.FindUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FindService {

    @Autowired
    private final FindMapper findMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

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

    public boolean changePassword(String email, String newPassword) {
        try {
            Optional<FindUserDTO> findUserDTO = findMapper.findPwdByEmail(email);

            if (findUserDTO.isPresent()) {
                FindUserDTO findUser = findUserDTO.get();

                String encodedPassword = passwordEncoder.encode(newPassword);
                findUser.setPassword(encodedPassword);
                findMapper.savePwd(findUser);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println("Error occurred while changing password: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

}
