package com.binge.securitydemo.event.listener;

import com.binge.securitydemo.event.MyEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @program: security-demo
 * @description:
 * @author: HJB
 * @create: 2022-07-21 10:47
 **/
@Component
@Slf4j
public class MyEventListener {
    @EventListener
    public void listener1(MyEvent myEvent) {
        log.info("source1: {} data: {}", myEvent.getSource().getClass().getSimpleName(), myEvent.getData());
    }

    @EventListener
    public void listener2(MyEvent myEvent) {
        log.info("source2: {} data: {}", myEvent.getSource().getClass().getSimpleName(), myEvent.getData());
    }
}
