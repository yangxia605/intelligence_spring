package com.intelligent.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class testController {

    @RequestMapping(value = "/hello")
    public String hello() {
        return "hello,this is a springboot demo";
    }
}
