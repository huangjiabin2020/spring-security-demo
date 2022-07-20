package com.binge.securitydemo.crud.mysql.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.binge.securitydemo.crud.mysql.entity.SysRole;
import com.binge.securitydemo.crud.mysql.mapper.SysRoleMapper;
import com.binge.securitydemo.crud.mysql.service.SysRoleService;
import org.springframework.stereotype.Service;

/**
 * @author Administrator
 * @description 针对表【sys_role(角色表)】的数据库操作Service实现
 * @createDate 2022-06-28 12:34:42
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole>
        implements SysRoleService {

}




