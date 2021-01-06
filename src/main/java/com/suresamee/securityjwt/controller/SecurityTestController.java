package com.suresamee.securityjwt.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityTestController {
    @GetMapping("/admin/get")
    public String getAdmin(){
        return "hi admin";
    }

    @GetMapping("/user/get")
    public String getUser(){
        return "hi user";
    }
}
