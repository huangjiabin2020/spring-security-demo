//package com.binge.securitydemo.exception;
//
//import com.binge.securitydemo.util.ResponseUtil;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import java.util.Map;
//
///**
// * @program: security-demo
// * @description:
// * @author: Mr.Huang
// * @create: 2022-07-02 16:26
// **/
//@ControllerAdvice
//public class GlobalExceptionController {
//    @ExceptionHandler(MyException.class)
//    @ResponseBody
//    public Map<String, Object> handler(MyException e) {
//
//        return ResponseUtil.resultCode(501, e.getMessage());
//    }
//}
