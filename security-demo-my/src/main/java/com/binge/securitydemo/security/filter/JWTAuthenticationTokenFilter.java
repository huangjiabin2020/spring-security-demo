package com.binge.securitydemo.security.filter;

import com.alibaba.fastjson.JSONObject;
import com.binge.securitydemo.security.entity.SecuritySysUser;
import com.binge.securitydemo.util.ResponseUtil;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @program: security-demo
 * @description:
 * @author: Mr.Huang
 * @create: 2022-06-28 15:56
 **/
@Slf4j
public class JWTAuthenticationTokenFilter extends BasicAuthenticationFilter {
    public JWTAuthenticationTokenFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
        log.error("JWTAuthenticationTokenFilter filter 初始化");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.error("JWTAuthenticationTokenFilter doFilterInternal");
        // 获取请求头中JWT的Token
        String token = request.getHeader("Authorization");
        if (token != null) {
            // 解析JWT
            Claims claims = null;
            try {
                claims = Jwts.parser()
                        .setSigningKey("JWTSecret")
                        //这一步如果检查到jwt过期 会报异常
                        .parseClaimsJws(token)
                        .getBody();
            } catch (ExpiredJwtException e) {
                //这里可以自定义不同的错误码 来区分JWT是过期还是其他异常
                ResponseUtil.responseJson(response, ResponseUtil.resultCode(501, e.getMessage()));
                return;
            } catch (UnsupportedJwtException | MalformedJwtException | SignatureException |
                     IllegalArgumentException e) {
                ResponseUtil.responseJson(response, ResponseUtil.resultCode(502, e.getMessage()));
                return;
            }
            // 获取用户名
            String username = claims.getSubject();
            String userId = claims.getId();
            if (!StringUtils.isEmpty(username) && !StringUtils.isEmpty(userId)) {
                // 获取角色
                List<GrantedAuthority> authorities = new ArrayList<>();
                String authority = claims.get("authorities").toString();
                if (!StringUtils.isEmpty(authority)) {
                    List<Map<String, String>> authorityMap = JSONObject.parseObject(authority, List.class);
                    for (Map<String, String> role : authorityMap) {
                        if (!StringUtils.isEmpty(role)) {
                            authorities.add(new SimpleGrantedAuthority(role.get("authority")));
                        }
                    }
                }
                //组装参数
                SecuritySysUser securitySysUser = new SecuritySysUser();
                securitySysUser.setUsername(claims.getSubject());
                securitySysUser.setUserId(Long.parseLong(claims.getId()));
                securitySysUser.setAuthorities(authorities);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(securitySysUser, userId, authorities);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } else {
            log.error("jwt 为空");
        }
        chain.doFilter(request, response);
    }
}
