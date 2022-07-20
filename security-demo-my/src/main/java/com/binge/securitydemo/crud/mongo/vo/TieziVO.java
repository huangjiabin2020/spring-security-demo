package com.binge.securitydemo.crud.mongo.vo;

import lombok.Data;

import java.util.Date;

/**
 * @program: security-demo
 * @description:
 * @author: HJB
 * @create: 2022-07-20 18:44
 **/
@Data
public class TieziVO {
    private String id;
    private Integer userId;
    /**
     * 帖子所属板块
     */
    private Integer moduleId;
    private String title;
    private String content;
    private Date createTime;
    private Date updateTime;
}
