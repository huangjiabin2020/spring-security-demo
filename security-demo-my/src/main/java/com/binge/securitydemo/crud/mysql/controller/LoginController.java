package com.binge.securitydemo.crud.mysql.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import com.binge.securitydemo.common.CommonConstant;
import com.binge.securitydemo.crud.mysql.service.SysUserService;
import com.binge.securitydemo.exception.MyException;
import com.binge.securitydemo.security.entity.SecuritySysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @program: security-demo
 * @description:
 * @author: Mr.Huang
 * @create: 2022-07-01 11:16
 **/
@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private SysUserService sysUserService;

    @PostMapping("/userLogin")
    public Object test(@RequestBody SecuritySysUser user) {
        System.out.println(user);
        return null;
    }

    @GetMapping("/getCode")
    //todo 加上限流 当然后续可以考虑分布式限流
    public void getCode(HttpServletResponse response, @RequestParam("username") String username) throws IOException {
        if (ObjectUtils.isEmpty(username)) {
            throw new MyException("请先输入用户名");
        }
        if (!sysUserService.checkUserExist(username)) {
            throw new MyException("用户名不存在");
        }
        //HuTool定义图形验证码的长和宽,验证码的位数，干扰线的条数
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(116, 36, 4, 10);
        //将验证码放入session
//        session.setAttribute("code",lineCaptcha.getCode()); //本项目基于jwt而非session
        redisTemplate.opsForValue().set(CommonConstant.LOGIN + username, lineCaptcha.getCode(), 1, TimeUnit.MINUTES);
        try {
            ServletOutputStream outputStream = response.getOutputStream();
            lineCaptcha.write(outputStream);
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
