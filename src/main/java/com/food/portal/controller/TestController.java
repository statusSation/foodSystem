package com.food.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/api/ww")
public class TestController {

    @RequestMapping("index")
    public String index() throws IOException {
        return "sss";
    }
    
    @RequestMapping("main")
    public String test() throws IOException {
        return "sss";
    }
    
    @RequestMapping("sss1")
    public String sss1(HttpServletRequest request) throws IOException {
        return "sss1";
    }
    
    @RequestMapping("login")
    public String login() throws IOException {
        return "login";
    }
    @RequestMapping("test")
    public String test1(HttpServletRequest request) throws IOException {
        return "test";
    }
    
}
