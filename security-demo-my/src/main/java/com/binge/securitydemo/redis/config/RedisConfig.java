package com.binge.securitydemo.redis.config;

import com.alibaba.fastjson.parser.ParserConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.security.core.GrantedAuthority;

/**
 * @program: security-demo
 * @description:
 * @author: Mr.Huang
 * @create: 2022-07-02 14:43
 **/
@Configuration
public class RedisConfig {


    @Bean
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
        // 使用fastJson序列化
        FastJsonRedisSerializer fastJsonRedisSerializer = new FastJsonRedisSerializer(Object.class);
        // value值的序列化采用fastJsonRedisSerializer
        redisTemplate.setValueSerializer(fastJsonRedisSerializer);
        redisTemplate.setHashValueSerializer(fastJsonRedisSerializer);
        // 全局开启AutoType，不建议使用
        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
        //自定义反序列化方法
        ParserConfig.getGlobalInstance().putDeserializer(GrantedAuthority.class, new SimpleGrantedAuthorityDeserializer());


        // 建议使用这种方式，小范围指定白名单，需要序列化的类
//        ParserConfig.getGlobalInstance().addAccept("com.avatar");

        // key的序列化采用StringRedisSerializer
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        return redisTemplate;
    }


    // 把这个bean的name设置为redisTemplate，这样我们才能全面接管redisTemplate！
//    @Bean(name = "redisTemplate")
//    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
//        RedisTemplate<String, Object> template = new RedisTemplate<>();
//        template.setConnectionFactory(redisConnectionFactory);
//        // jackson序列化所有的类
//        Jackson2JsonRedisSerializer Jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
//        // jackson序列化的一些配置
//        ObjectMapper om = new ObjectMapper();
//        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//        om.activateDefaultTyping(LaissezFaireSubTypeValidator.instance);
//        Jackson2JsonRedisSerializer.setObjectMapper(om);
//        // String的序列化
//        StringRedisSerializer stringSerializer = new StringRedisSerializer();
//
//        //将我们的key采用String的序列化方式
//        template.setKeySerializer(stringSerializer);
//        //将我们的hash的key也采用String的序列化方式
//        template.setHashKeySerializer(stringSerializer);
//        //value采用jackson序列化方式
//        template.setValueSerializer(Jackson2JsonRedisSerializer);
//        //hash的value也采用jackson序列化方式
//        template.setHashValueSerializer(Jackson2JsonRedisSerializer);
//
//        template.afterPropertiesSet();
//
//
//        return template;
//    }
}
