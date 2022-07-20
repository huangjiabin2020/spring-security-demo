package com.binge.securitydemo.crud.mysql.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.binge.securitydemo.crud.mysql.entity.SysRole;
import com.binge.securitydemo.crud.mysql.entity.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Administrator
 * @description 针对表【sys_user(系统用户表)】的数据库操作Mapper
 * @createDate 2022-06-28 12:34:42
 * @Entity generator.com.binge.securitydemo.SysUser
 */
public interface SysUserMapper extends BaseMapper<SysUser> {

    List<SysRole> selectSysRoleByUserId(@Param("userId") Long userId);
}




