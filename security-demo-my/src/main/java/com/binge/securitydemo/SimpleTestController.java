package com.binge.securitydemo;

import com.binge.securitydemo.crud.mongo.mapper.StudentDaoTypeOne;
import org.springframework.beans.factory.annotation.Autowired;
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
public class SimpleTestController {
    @Autowired
    private StudentDaoTypeOne studentDaoTypeOne;

    @GetMapping
    public void getOneStudentByStudentId(){
        System.out.println(studentDaoTypeOne.findById("student_1"));
    }

//    @GetMapping
    public void test(@RequestParam("className") String className, @RequestParam("field") String field,
                     @RequestParam("fieldValue") String fieldValue){

    }
}
