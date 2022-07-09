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
public class UsernamePasswordToken extends UsernamePasswordAuthenticationToken {

    public UsernamePasswordToken(Object principal, Object credentials) {
        super(principal, credentials);
    }

    public UsernamePasswordToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }
}
