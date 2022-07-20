package com.binge.securitydemo.crud.mysql.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.binge.securitydemo.crud.mysql.entity.SysMenu;
import com.binge.securitydemo.crud.mysql.entity.SysRole;
import com.binge.securitydemo.crud.mysql.entity.SysUser;
import com.binge.securitydemo.crud.mysql.mapper.SysMenuMapper;
import com.binge.securitydemo.crud.mysql.mapper.SysUserMapper;
import com.binge.securitydemo.crud.mysql.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Administrator
 * @description 针对表【sys_user(系统用户表)】的数据库操作Service实现
 * @createDate 2022-06-28 12:34:42
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser>
        implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Override
    public List<SysRole> selectSysRoleByUserId(Long userId) {
        List<SysRole> list = sysUserMapper.selectSysRoleByUserId(userId);
        return list;
    }

    @Override
    public List<SysMenu> selectSysMenuByUserId(Long userId) {

        return sysMenuMapper.selectSysMenuByUserId(userId);
    }
}




