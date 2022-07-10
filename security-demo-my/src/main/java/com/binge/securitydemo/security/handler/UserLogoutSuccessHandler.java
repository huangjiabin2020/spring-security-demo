package com.binge.securitydemo.security.handler;

import com.binge.securitydemo.security.entity.SecuritySysUser;
import com.binge.securitydemo.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: security-demo
 * @description:
 * @author: Mr.Huang
 * @create: 2022-06-28 15:52
 **/
@Component
public class UserLogoutSuccessHandler implements LogoutSuccessHandler {
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        //清除redis相关缓存
//        String jwt = httpServletRequest.getHeader("Authorization");
        SecuritySysUser securitySysUser = (SecuritySysUser) authentication.getPrincipal();
        redisTemplate.delete(securitySysUser.getUsername());
        Map<String, Object> resultData = new HashMap<>();
        resultData.put("code", "200");
        resultData.put("message", "登出成功");
        SecurityContextHolder.clearContext();
        ResponseUtil.responseJson(httpServletResponse, ResponseUtil.resultSuccess(resultData));
    }
}
