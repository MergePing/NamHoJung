package com.ohgiraffers.mergyping.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminReportController {

    @GetMapping("/report")
    public String showReportPage() {
        return "user/admin/adminreport";
    }
}