package com.ohgiraffers.mergyping.auth.model.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
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
        helper.setSubject("남호정 가입 이메일 인증");
        String htmlContent = "<html>" + "<body>"
                + "<h1>남호정</h1>"
                + "<h2>이메일 인증 코드</h2>"
                + "<h3 style='text-align: center; font-weight: 900; position: absolute; left: 910px; top:100px; color:black; font-style: italic; text-shadow: -1px 0px yellow, 0px 1px yellow, 1px 0px yellow, 0px -1px yellow;'>"
                + authCode + "</h3>"
                + "<p style='color:orange; text-align: center; position: absolute; left: 765px; top:150px; font-weight: 900; font-style: italic;'>인증번호의 유효시간은 이메일을 받은 시간부터 30분입니다.</p>"
                + "<p style='color:greenyellow; text-align: center; position: absolute; left: 870px; top:200px; font-weight: 900;'>남호정 운영진(MergyTeam)</p>"
                + "<img src='cid:mailImage' alt='배경' style='position:absolute; z-index: -1'>";
        helper.setText(htmlContent, true);

        // 이메일에 이미지 첨부 -> 수정필요
        ClassPathResource image = new ClassPathResource("static/images/email/img.png");
        helper.addInline("mailImage", image);


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
