package com.ohgiraffers.mergyping.user.controller;

import com.ohgiraffers.mergyping.user.model.dto.MainDTO;
import com.ohgiraffers.mergyping.user.model.dto.MyPageDTO;
import com.ohgiraffers.mergyping.user.model.service.MainService;
import com.ohgiraffers.mergyping.user.model.service.MyPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class MainController {

    private final MyPageService myPageService;
    private final MainService mainService;

    @Autowired public MainController(MyPageService myPageService, MainService mainService) {
        this.myPageService = myPageService;
        this.mainService = mainService;
    }

    @GetMapping({"/", "/main"})
    public String bestPost(Model model){
        List<MainDTO> bestPostList = mainService.bestPost();
        model.addAttribute("bestPosts", bestPostList);

        MyPageDTO myPageDTO = myPageService.findNickName();
        model.addAttribute("myPageDTO", myPageDTO);

        MyPageDTO myPageDTO2 = myPageService.findEmail();
        model.addAttribute("myPageDTO2", myPageDTO2);

        return  "main/main";
    }



    @GetMapping("/intro")
    public String intro(){return "/main/intro/intro";}

}
