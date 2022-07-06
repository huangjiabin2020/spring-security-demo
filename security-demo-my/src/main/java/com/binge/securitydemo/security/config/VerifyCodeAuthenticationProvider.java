package com.binge.securitydemo.security.config;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.binge.securitydemo.crud.entity.SysRole;
import com.binge.securitydemo.crud.entity.SysUser;
import com.binge.securitydemo.crud.service.SysUserService;
import com.binge.securitydemo.security.entity.SecuritySysUser;
import com.binge.securitydemo.security.token.VerifyCodeToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @program: security-demo
 * @description: 验证码相关校验逻辑
 * @author: Mr.Huang
 * @create: 2022-07-02 10:01
 **/
@Component
@Slf4j
public class VerifyCodeAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private SysUserService sysUserService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String userName = (String) authentication.getPrincipal();
        String code = (String) authentication.getCredentials();
        log.info("验证码登录: {}---{}", userName, code);
        //todo 去redis查询 判断是否存在 这里简单写死用来测试
        if (code.equals("1111")) {
            throw new BadCredentialsException("验证码错误");
        }
        // 这里可以将用户名存入redis 查不到再查库 以提升速度
        SysUser one = sysUserService.getOne(new LambdaQueryWrapper<SysUser>().eq(SysUser::getUsername, userName));
        if (one == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        //组装springsecurity需要的用户实体类
        SecuritySysUser securitySysUser = new SecuritySysUser();
        BeanUtils.copyProperties(one, securitySysUser);
        // 查询用户角色
        List<SysRole> sysRoleEntityList = sysUserService.selectSysRoleByUserId(securitySysUser.getUserId());
        // 角色合集
        Set<GrantedAuthority> authorities = new HashSet<>();
        for (SysRole sysRoleEntity : sysRoleEntityList) {
            //在我们返回的UserDetails的Authority需要加ROLE_前缀，Controller上使用时不要加前缀；
            authorities.add(new SimpleGrantedAuthority("ROLE_" + sysRoleEntity.getRoleName()));
        }
        //设置该用户的角色列表
        securitySysUser.setAuthorities(authorities);
        return new VerifyCodeToken(securitySysUser, code, authorities);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
