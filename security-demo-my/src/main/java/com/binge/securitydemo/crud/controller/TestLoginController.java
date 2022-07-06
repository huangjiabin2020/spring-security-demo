package com.binge.securitydemo.crud.controller;

import com.binge.securitydemo.security.entity.SecuritySysUser;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: security-demo
 * @description:
 * @author: Mr.Huang
 * @create: 2022-07-01 11:16
 **/
@RestController
@RequestMapping("/login")
public class TestLoginController {
    @PostMapping("/userLogin")
    public Object test(@RequestBody SecuritySysUser user) {
        System.out.println(user);
        return null;
    }
}
