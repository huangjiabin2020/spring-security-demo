package com.binge.securitydemo;

import com.binge.securitydemo.crud.mapper.SysUserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SecurityDemoApplicationTests {
    @Autowired
    SysUserMapper sysUserMapper;

    @Test
    void contextLoads() {

    }

}
