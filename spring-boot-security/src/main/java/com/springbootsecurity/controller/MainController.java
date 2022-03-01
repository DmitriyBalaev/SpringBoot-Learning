package com.springbootsecurity.controller;

import com.springbootsecurity.persistence.model.User;
import com.springbootsecurity.service.MyUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class MainController {

    private MyUserDetailService userService;

    @Autowired
    public MainController(MyUserDetailService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String homePage(){
        return "home";
    }

    @GetMapping("/authenticated")
    public String pageForAuthenticatedUsers(Principal principal){
        User user = userService.findByUsername(principal.getName());
        return "secured part of web service " + user.getUsername() + " " +user.getEmail();
    }
}
