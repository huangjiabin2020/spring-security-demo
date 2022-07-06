package com.binge.securitydemo.security.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

/**
 * @program: security-demo
 * @description:
 * @author: Mr.Huang
 * @create: 2022-07-02 11:03
 **/
@Slf4j
public class UsernamePasswordFilter extends AbstractAuthenticationProcessingFilter {
    public UsernamePasswordFilter(String defaultFilterProcessesUrl) {
        super(defaultFilterProcessesUrl);
        log.error("UsernamePasswordFilter filter 初始化");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        if (!request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException(
                    "Authentication method not supported: " + request.getMethod());
        }
//        String username = request.getParameter("username");
//        String password = request.getParameter("password");
        //针对post请求 获取其中请求体的参数
        JSONObject postObject = JSON.parseObject(request.getReader().lines().collect(Collectors.joining(System.lineSeparator())));
        String username = (String) postObject.get("username");
        String password = (String) postObject.get("password");
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        log.error("UsernamePasswordFilter attemptAuthentication");
        return this.getAuthenticationManager().authenticate(usernamePasswordAuthenticationToken);

    }
}
