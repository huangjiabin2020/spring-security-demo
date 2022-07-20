package com.binge.securitydemo.crud.mongo.controller;

import com.binge.securitydemo.crud.mongo.entity.Tiezi;
import com.binge.securitydemo.crud.mongo.mapper.TieziDao;
import com.binge.securitydemo.crud.mongo.vo.TieziVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: security-demo
 * @description:
 * @author: HJB
 * @create: 2022-07-20 18:43
 **/
@RestController
@RequestMapping("/tiezi")
public class TieziController {
    @Autowired
    private TieziDao tieziDao;
    @PostMapping("add")
    public void add(@RequestBody TieziVO tieziVO){
        Tiezi tiezi = new Tiezi();
        BeanUtils.copyProperties(tieziVO,tiezi);
        tieziDao.save(tiezi);
    }

}
