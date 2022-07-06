package com.binge.securitydemo.security.token;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * @program: security-demo
 * @description:
 * @author: Mr.Huang
 * @create: 2022-07-02 10:55
 **/
public class VerifyCodeToken extends UsernamePasswordAuthenticationToken {

    public VerifyCodeToken(Object principal, Object credentials) {
        super(principal, credentials);
    }

    public VerifyCodeToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }
}
