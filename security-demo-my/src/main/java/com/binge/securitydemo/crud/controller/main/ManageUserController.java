package com.binge.securitydemo.crud.controller.main;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.binge.securitydemo.crud.entity.SysUser;
import com.binge.securitydemo.crud.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/main/user")
public class ManageUserController {
    @Autowired
    private SysUserService sysUserService;
    @GetMapping("/{cp}/{ps}")
    public  IPage<SysUser> showUserList(@PathVariable Integer cp,@PathVariable Integer ps){
        IPage<SysUser> page = sysUserService.page(new Page<SysUser>(cp, ps));
        return page;
    }
}
