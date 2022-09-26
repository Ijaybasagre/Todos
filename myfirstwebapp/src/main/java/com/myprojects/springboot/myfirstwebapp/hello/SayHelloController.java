package com.myprojects.springboot.myfirstwebapp.hello;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class SayHelloController {

    @RequestMapping("/say-hello")

    public String sayHello(){
        return "Hello! How are you?";
    }

    @RequestMapping("/sayhello")
    public String sayHelloHtml(){
        return "sayHello";
    }
}
