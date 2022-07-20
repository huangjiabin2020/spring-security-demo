package com.binge.securitydemo.crud.mongo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;

/**
 * @program: security-demo
 * @description:
 * @author: HJB
 * @create: 2022-07-20 15:10
 **/
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Data
public class Tiezi implements Serializable {
    @Id// 必须指定id列
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
