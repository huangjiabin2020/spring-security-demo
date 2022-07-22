package com.binge.securitydemo;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import com.binge.securitydemo.crud.mysql.mapper.SysUserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

//@SpringBootTest
class SecurityDemoApplicationTests {
    @Autowired
    SysUserMapper sysUserMapper;

    @Test
    void contextLoads() throws IOException {
        CircleCaptcha captcha = CaptchaUtil.createCircleCaptcha(200, 100, 4, 20);
        Image image = captcha.createImage("abcd");
        captcha.write(Files.newOutputStream(new File("./hutool.jpeg").toPath()));
    }

}
