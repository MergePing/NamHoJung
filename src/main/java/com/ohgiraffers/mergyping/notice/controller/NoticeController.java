package com.ohgiraffers.mergyping.notice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class NoticeController {


    @GetMapping("/notice")
    public String notice(){return "/notice/notice";}

    @GetMapping("/selectnotice")
    public String selectNotice(){return "/notice/selectnotice";}

}