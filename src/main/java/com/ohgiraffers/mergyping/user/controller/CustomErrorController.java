package com.ohgiraffers.mergyping.user.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController {
    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {

        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());

            if (statusCode == 404) {
                model.addAttribute("errorMessage", "Page not found");
                return "user/error/permission";
            } else if (statusCode == 500) {
                model.addAttribute("errorMessage", "Internal server error");
                return "user/error/server";
            }
        }
        model.addAttribute("errorMessage", "Unknown error occurred");
        return "user/error/page";
    }
}
