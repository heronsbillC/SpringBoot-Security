package com.clubfactory.demo.test.controller;

import com.clubfactory.demo.test.pojo.Users;
import com.clubfactory.demo.test.service.UserService;
import com.clubfactory.demo.test.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@Slf4j
@RequestMapping("/LoginController")
public class LoginController {

    @Resource
    private UserServiceImpl userService;

    @RequestMapping("/loginAction")
    public String loginAction(HttpServletRequest request){
        String name = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println("userName=" + name + "/tpassword=" + password);
        Users user = userService.login(name,password);
        log.info("LoginController.loginAction->user="+user);
        if(user != null)
            return "hello";
        else
            return "error";
    }

    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }

    @RequestMapping("/admin")
    public String admin() {
        return "admin";
    }

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/error")
    public String error() {
        return "error";
    }
}
