package com.chenfj.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: feiju.chen
 * @Date: 2023/2/10 9:41
 * @Description:
 * @version: 1.0
 */
@RestController
public class HelooController {

    @RequestMapping("hello")
    public String hello(){
        return "hello";
    }
}
