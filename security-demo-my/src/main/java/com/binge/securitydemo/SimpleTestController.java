package com.binge.securitydemo;

import com.binge.securitydemo.crud.mongo.mapper.StudentDaoTypeOne;
import com.binge.securitydemo.event.MyEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: security-demo
 * @description:
 * @author: HJB
 * @create: 2022-07-18 17:25
 **/
@RestController
@RequestMapping("/simple")
@Slf4j
public class SimpleTestController {
    @Autowired
    private StudentDaoTypeOne studentDaoTypeOne;

//    @GetMapping
    public void getOneStudentByStudentId(){
        System.out.println(studentDaoTypeOne.findById("student_1"));
    }

//    @GetMapping
    public void test(@RequestParam("className") String className, @RequestParam("field") String field,
                     @RequestParam("fieldValue") String fieldValue){

    }
    @Autowired
    ApplicationEventPublisher publisher;
    @GetMapping
    public void testEvent(){
        MyEvent myEvent = new MyEvent(this, "测试生产者产生事件的数据");
        publisher.publishEvent(myEvent);
        log.info("生产者 发送消息:{}",myEvent);
    }



}
