package com.binge.securitydemo.redis.config;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.lang.reflect.Type;

/**
 * @program: security-demo
 * @description:
 * @author: Mr.Huang
 * @create: 2022-07-04 10:31
 **/
/**
 * FastJson——自己实现SimpleGrantedAuthority类的反序列化方法。
 * 因为SimpleGrantedAuthority这个类没有无参构造方法 反序列化的时候没法new这个对象
 * 所以需要自己定义反序列化方法 针对GrantedAuthority这个类进行反序列化的时候，调用以下的deserialze方法
 */
public class SimpleGrantedAuthorityDeserializer implements ObjectDeserializer {
    @Override
    public GrantedAuthority deserialze(DefaultJSONParser parser, Type type, Object fieldName) {
        JSONObject jsonObject = parser.parseObject();
        String authority = String.valueOf(jsonObject.get("authority"));
        return new SimpleGrantedAuthority(authority);
    }

    @Override
    public int getFastMatchToken() {
        return 0;
    }
}
