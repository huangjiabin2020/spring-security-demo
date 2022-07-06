package com.binge.securitydemo.security.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.binge.securitydemo.security.token.VerifyCodeToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

/**
 * @program: security-demo
 * @description: 拦截验证码登录请求
 * @author: Mr.Huang
 * @create: 2022-07-02 10:53
 **/
@Slf4j
public class VerifyCodeFilter extends AbstractAuthenticationProcessingFilter {
    public VerifyCodeFilter(String defaultFilterProcessesUrl) {
        super(defaultFilterProcessesUrl);
        log.error("VerifyCodeFilter filter 初始化");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        if (!request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException(
                    "Authentication method not supported: " + request.getMethod());
        }
        JSONObject postObject = JSON.parseObject(request.getReader().lines().collect(Collectors.joining(System.lineSeparator())));
        String username = (String) postObject.get("username");
        String code = (String) postObject.get("code");
        VerifyCodeToken verifyCodeToken = new VerifyCodeToken(username, code);
//        verifyCodeToken.setDetails(authenticationDetailsSource.buildDetails(request));
        log.error("VerifyCodeFilter attemptAuthentication");
        return this.getAuthenticationManager().authenticate(verifyCodeToken);
    }
}
