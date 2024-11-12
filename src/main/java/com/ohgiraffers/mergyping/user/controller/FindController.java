package com.ohgiraffers.mergyping.user.controller;

import com.ohgiraffers.mergyping.user.model.dto.FIndUserDTO;
import com.ohgiraffers.mergyping.user.model.dto.UserDTO;
import com.ohgiraffers.mergyping.user.model.service.FindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RequestMapping("/find")
@Controller
public class FindController {

    @Autowired
    private FindService findService;

    @GetMapping("/select")
    public String search() {

        return "/user/searchuser/searchuser";
    }

    @GetMapping("/id")
    public String serachId() {

        return "/user/searchuser/searchId";
    }

    @GetMapping("/findid")
    public ResponseEntity<Map<String, Object>> findId(@RequestParam String userEmail) {
        Map<String, Object> response = new HashMap<>();
        Optional<FIndUserDTO> findUserDTO = findService.findId(userEmail);
        if (findUserDTO.isPresent()) {
            response.put("success", true);
            response.put("userId", findUserDTO.get().getId());
            return ResponseEntity.ok(response);
        } else {
            response.put("success", false);
            response.put("message", "아이디를 찾을 수 없습니다.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
}
