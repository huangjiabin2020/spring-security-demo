package com.binge.securitydemo.crud.mongo.mapper;

import com.binge.securitydemo.crud.mongo.entity.Tiezi;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @program: security-demo
 * @description:
 * @author: HJB
 * @create: 2022-07-20 18:47
 **/
public interface TieziDao extends MongoRepository<Tiezi, String> {
}
