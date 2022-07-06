package com.binge.securitydemo.security.handler;

import com.binge.securitydemo.util.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: security-demo
 * @description:
 * @author: Mr.Huang
 * @create: 2022-06-28 15:29
 **/
@Component
@Slf4j
public class UserLoginFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        // 这些对于操作的处理类可以根据不同异常 返回不同的code和msg
        if (exception instanceof UsernameNotFoundException) {
            log.info("【登录失败】" + exception.getMessage());
            ResponseUtil.responseJson(response, ResponseUtil.resultCode(500, exception.getMessage()));
        }
        if (exception instanceof LockedException) {
            log.info("【登录失败】" + exception.getMessage());
            ResponseUtil.responseJson(response, ResponseUtil.resultCode(500, exception.getMessage()));
        }
        if (exception instanceof BadCredentialsException) {
            log.info("【登录失败】" + exception.getMessage());
            ResponseUtil.responseJson(response, ResponseUtil.resultCode(500, exception.getMessage()));
        }
        ResponseUtil.responseJson(response, ResponseUtil.resultCode(500, "登录失败"));
    }
}
