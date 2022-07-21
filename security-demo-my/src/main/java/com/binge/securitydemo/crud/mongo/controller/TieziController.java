package com.binge.securitydemo.crud.mongo.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.binge.securitydemo.crud.common.vo.IdsVO;
import com.binge.securitydemo.crud.mongo.entity.Tiezi;
import com.binge.securitydemo.crud.mongo.mapper.TieziDao;
import com.binge.securitydemo.crud.mongo.vo.TieziVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
    public void add(@RequestBody TieziVO tieziVO) {
        Tiezi tiezi = new Tiezi().setCreateTime(new Date());
        BeanUtil.copyProperties(tieziVO, tiezi, CopyOptions.create().setIgnoreNullValue(true));
        tieziDao.save(tiezi);
    }

    @PostMapping("update")
    public void update(@RequestBody TieziVO tieziVO){
        Tiezi tiezi = new Tiezi().setUpdateTime(new Date());
        BeanUtil.copyProperties(tieziVO, tiezi, CopyOptions.create().setIgnoreNullValue(true));
        tieziDao.save(tiezi);
    }



    @GetMapping
    public Object getAll() {
        return tieziDao.findAll();
    }

    @GetMapping("/{title}/{cp}/{ps}")
    public Object getPage(@PathVariable("title") String title,
                          @PathVariable("cp") Integer cp,
                          @PathVariable("ps") Integer ps) {
        ExampleMatcher exampleMatcher = ExampleMatcher.matching().withMatcher("title", ExampleMatcher.GenericPropertyMatchers.contains());
        //要查询的数据放到这个类里面 然后将查询条件放到exampleMatcher里面
        Tiezi tiezi = new Tiezi()
                .setTitle(title);
        Example<Tiezi> example = Example.of(tiezi, exampleMatcher);
        //这个分页是的cp是从0开始计算的 所以一般从第一页开始的cp需要-1
        Page<Tiezi> tieziPage = tieziDao.findAll(example, PageRequest.of(cp - 1, ps));
        return tieziPage;
    }


    @DeleteMapping
    public void deleteByIds(@RequestBody IdsVO idsVO){
//        批量删除 需要构造出实体类列表 关键的ID列进行赋值 根据ID进行删除
        List<Tiezi> collect = idsVO.getIdsStr().stream().map(x -> new Tiezi().setId(x)).collect(Collectors.toList());
        tieziDao.deleteAll(collect);
    }


}
