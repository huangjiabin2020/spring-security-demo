package com.binge.securitydemo.event;

import lombok.*;
import org.springframework.context.ApplicationEvent;

/**
 * @program: security-demo
 * @description:
 * @author: HJB
 * @create: 2022-07-21 10:42
 **/
@Getter
@Setter
public class MyEvent extends ApplicationEvent {
    private String data;
    public MyEvent(Object source) {
        super(source);
    }

    public MyEvent(Object source, String data) {
        super(source);
        this.data = data;
    }


}
