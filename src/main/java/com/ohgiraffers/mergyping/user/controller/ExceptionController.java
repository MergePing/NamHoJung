package com.ohgiraffers.mergyping.user.controller;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(AccessDeniedException.class)
    public String permissionError() {

        return "/user/error/permission";
    }
    @ExceptionHandler(NoHandlerFoundException.class)
    public String pageError() {

        return "/user/error/page";
    }

    @ExceptionHandler(Exception.class)
    public String serverError() {

        return "/user/error/server";
    }
}
