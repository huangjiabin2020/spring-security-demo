package com.binge.securitydemo.redis.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @program: security-demo
 * @description:
 * @author: HJB
 * @create: 2022-07-21 19:06
 **/
@Component
@Slf4j
public class RedisKeyExpirationListener extends KeyExpirationEventMessageListener {
    @Autowired
    private RedisTemplate redisTemplate;

    public RedisKeyExpirationListener(RedisMessageListenerContainer listenerContainer) {
        super(listenerContainer);
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {
        /*
        注意:过期key没发获取它的value值
        解决办法:
        1. 在key上添加value值
        2. 在set key 的时候 同时set一个备用key,用来保存value值, 这个备用key的过期时间应该比key的过期时间更长
        例如: set order 1 一小时后过期; 同时 set order_bak 具体的订单数据 一小时五分钟后过期
        这样可以在拿到过期order的key 拼接上后缀 去拿备用key保存的数据
         */
        String expiredKey = message.toString();
        log.error("======过期啦!====>"+expiredKey);
        if(expiredKey.startsWith("binge:")){
            /**
             * TODO
             * 如果是自己想要监控的KEY, 则可以在这里处理业务
             */
            log.error("======过期啦!处理业务中====>");
        }
    }

    /**
     * 用来测试而已
     */
//    @Scheduled(cron = "0/3 * * * * ?")
    public void test(){
        log.info("添加redis缓存");
        redisTemplate.opsForValue().set("binge:"+UUID.randomUUID().toString(),1,3, TimeUnit.SECONDS);
    }
}
