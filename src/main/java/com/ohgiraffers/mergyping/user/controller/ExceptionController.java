package com.ohgiraffers.mergyping.user.controller;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(AccessDeniedException.class)
    public String handleAccessDeniedException(AccessDeniedException ex, Model model) {
        model.addAttribute("errorMessage", ex.getMessage());
        return "/user/error/permission";
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ModelAndView handleNoHandlerFoundException(NoHandlerFoundException ex) {
        ModelAndView modelAndView = new ModelAndView("/user/error/page");
        modelAndView.addObject("errorMessage", "Page not found");
        return modelAndView;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleAllExceptions(Exception ex) {
        ModelAndView modelAndView = new ModelAndView("/user/error/server");
        modelAndView.addObject("errorMessage", ex.getMessage());
        return modelAndView;
    }
}
