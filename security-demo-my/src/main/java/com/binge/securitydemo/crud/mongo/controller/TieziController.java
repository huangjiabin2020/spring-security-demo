package com.binge.securitydemo.crud.mongo.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.binge.securitydemo.crud.mongo.entity.Tiezi;
import com.binge.securitydemo.crud.mongo.mapper.TieziDao;
import com.binge.securitydemo.crud.mongo.vo.TieziVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

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
        Tiezi tiezi = new Tiezi().setCreateTime(new Date());
        BeanUtil.copyProperties(tieziVO,tiezi, CopyOptions.create().setIgnoreNullValue(true));
        tieziDao.save(tiezi);
    }

    @GetMapping
    public Object getAll(){
        return tieziDao.findAll();
    }

    @GetMapping("/{cp}/{ps}")
    public Object getPage(@PathVariable("cp") Integer cp,
                          @PathVariable("ps") Integer ps){
        //这个分页是的cp是从0开始计算的
        Page<Tiezi> tieziPage = tieziDao.findAll(PageRequest.of(cp-1, ps));

        return tieziPage;
    }



}
