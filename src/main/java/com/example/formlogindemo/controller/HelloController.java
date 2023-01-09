package com.example.formlogindemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HelloController {

    @GetMapping("/hello")
    public String hello(){
        return "hello.html";
    }

    @ResponseBody
    @GetMapping("/error1")
    public String error(){
        return "error.html";
    }
}
