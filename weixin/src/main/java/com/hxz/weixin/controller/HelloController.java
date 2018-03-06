package com.hxz.weixin.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @RequestMapping(method = RequestMethod.GET)
    public String Hello(){
        return "hello";
    }

    @RequestMapping(value = "/setSession",method = RequestMethod.GET)
    public Object setSession(HttpServletRequest request, HttpServletResponse response){

        HttpSession session = request.getSession();
        session.setAttribute("name",123);
        session.setMaxInactiveInterval(5);
        return session.getAttribute("name");
    }

    @RequestMapping(value = "/getSession",method = RequestMethod.GET)
    public Object getSession(HttpServletRequest request, HttpServletResponse response){

        HttpSession session = request.getSession();
        return session.getAttribute("name");
    }
}
