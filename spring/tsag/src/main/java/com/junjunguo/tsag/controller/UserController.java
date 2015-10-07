package com.junjunguo.tsag.controller;

import com.junjunguo.tsag.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * This file is part of tsag
 * <p/>
 * Created by <a href="http://junjunguo.com">GuoJunjun</a> on October 06, 2015.
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;

//    @Scope("prototype")
//    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(
//            @RequestParam("email")
            String email,
//            @RequestParam("password")
            String password) {
        return this.userService.login(email, password);
    }

//    @Scope("prototype")
//    @ResponseBody
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(
//            @RequestParam("username")
            String username,
//            @RequestParam("email")
            String email,
//            @RequestParam("password")
            String password) {
        return this.userService.register(username, password, email);

    }
}
