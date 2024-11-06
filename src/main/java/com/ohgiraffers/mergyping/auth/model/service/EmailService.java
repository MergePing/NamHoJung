package com.ohgiraffers.mergyping.auth.model.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class EmailService {
    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;

    @Value("${spring.mail.password}")
    private String emailPassword;

    @Value("${spring.mail.auth-code-expiration-millis}")
    private long authCodeExpirationMillis;  // 인증 코드 만료 시간 (milliseconds)

    private final Map<String, AuthCodeInfo> emailAuthCodes = new HashMap<>();

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    private String generateAuthCode() {
        Random random = new Random();
        StringBuilder authCode = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            authCode.append(random.nextInt(10)); // 0부터 9까지 숫자
        }
        return authCode.toString();
    }

    private void sendEmail(String toEmail, String authCode) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom(fromEmail);
        helper.setTo(toEmail);
        helper.setSubject("이메일 인증 코드");
        helper.setText("귀하의 인증 번호는 " + authCode + "입니다.");

        mailSender.send(message);
    }

    public boolean sendAuthCode(String email) {
        String authCode = generateAuthCode();  // 6자리 인증 코드 생성
        long expirationTime = System.currentTimeMillis() + authCodeExpirationMillis;  // 만료 시간 계산

        emailAuthCodes.put(email, new AuthCodeInfo(authCode, expirationTime));

        try {
            sendEmail(email, authCode);
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean verifyAuthCode(String email, String authCode) {
        AuthCodeInfo storedAuthCodeInfo = emailAuthCodes.get(email);

        if (storedAuthCodeInfo == null) {
            return false;
        }

        if (System.currentTimeMillis() > storedAuthCodeInfo.getExpirationTime()) {
            emailAuthCodes.remove(email);
            return false;
        }

        return storedAuthCodeInfo.getAuthCode().equals(authCode);
    }

    private static class AuthCodeInfo {
        private final String authCode;
        private final long expirationTime;

        public AuthCodeInfo(String authCode, long expirationTime) {
            this.authCode = authCode;
            this.expirationTime = expirationTime;
        }

        public String getAuthCode() {
            return authCode;
        }

        public long getExpirationTime() {
            return expirationTime;
        }
    }
}
