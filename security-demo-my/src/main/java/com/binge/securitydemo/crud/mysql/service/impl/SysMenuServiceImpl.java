package com.binge.securitydemo.crud.mysql.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.binge.securitydemo.crud.mysql.entity.SysMenu;
import com.binge.securitydemo.crud.mysql.mapper.SysMenuMapper;
import com.binge.securitydemo.crud.mysql.service.SysMenuService;
import org.springframework.stereotype.Service;

/**
 * @author Administrator
 * @description 针对表【sys_menu(权限表)】的数据库操作Service实现
 * @createDate 2022-06-28 12:34:42
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu>
        implements SysMenuService {

}




