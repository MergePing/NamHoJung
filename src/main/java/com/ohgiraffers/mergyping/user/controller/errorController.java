package com.ohgiraffers.mergyping.user.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/error")
public class errorController {

    @GetMapping("/permission")
    public String permissionError() {

        return "/user/error/403";
    }

    @GetMapping("/page")
    public String pageError() {

        return "/user/error/404";
    }

    @GetMapping("/server")
    public String serverError() {

        return "/user/error/500";
    }
}
