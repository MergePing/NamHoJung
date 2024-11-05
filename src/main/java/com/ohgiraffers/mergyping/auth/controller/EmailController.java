package com.ohgiraffers.mergyping.auth.controller;

import com.ohgiraffers.mergyping.auth.model.service.EmailService;
import com.ohgiraffers.mergyping.user.model.dto.EmailDTO;
import com.ohgiraffers.mergyping.user.model.dto.EmailMessageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class EmailController {

    private final EmailService emailService;

    @Autowired
    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/sendauthCode")
    public ResponseEntity<EmailMessageDTO> sendAuthCode(@RequestBody EmailDTO request) {
        if (request.getEmail() == null || request.getEmail().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new EmailMessageDTO(false, "이메일을 입력해주세요."));
        }

        boolean success = emailService.sendAuthCode(request.getEmail());
        if (success) {
            return ResponseEntity.ok(new EmailMessageDTO(true, "인증 코드가 발송되었습니다."));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new EmailMessageDTO(false, "인증 코드 발송 실패."));
        }
    }

    @PostMapping("/verifyauthCode")
    public ResponseEntity<EmailMessageDTO> verifyAuthCode(@RequestBody EmailDTO request) {
        if (request.getEmail() == null || request.getEmail().isEmpty() || request.getAuthCode() == null || request.getAuthCode().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new EmailMessageDTO(false, "이메일과 인증 코드를 모두 입력해주세요."));
        }

        boolean success = emailService.verifyAuthCode(request.getEmail(), request.getAuthCode());
        if (success) {
            return ResponseEntity.ok(new EmailMessageDTO(true, "인증 성공."));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new EmailMessageDTO(false, "인증 번호가 잘못되었습니다."));
        }
    }
}