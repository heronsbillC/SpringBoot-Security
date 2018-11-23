package com.clubfactory.demo.test.controller;

import com.clubfactory.demo.test.pojo.Users;
import com.clubfactory.demo.test.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@Slf4j
@RequestMapping("/LoginController")
public class LoginController {

    @Resource
    private UserServiceImpl userService;

    @PostMapping("/loginAction")
    public String loginAction(HttpServletRequest request){
        String name = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println("userName=" + name + "   password=" + password);
        Users user = userService.login(name,password);
        log.info("LoginController.loginAction->user="+user);
        if(user != null)
            return "hello";
        else
            return "error";
    }

    @RequestMapping("/home")
    public String home() {
        return "home";
    }
    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }

//    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/admin")
    public String admin(HttpServletRequest request) {
        System.out.println("session->" + request.getSession().getAttribute("username"));
        return "admin";
    }

//    @PreAuthorize("hasRole('USER')")
    @RequestMapping(value = "/user")
    public String user() {
        return "user";
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
