package com.junjunguo.spring.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This file is part of demo
 * <p/>
 * Created by <a href="http://junjunguo.com">GuoJunjun</a> on October 02, 2015.
 */
@RestController
public class ExampleController {
    @RequestMapping("/")
    public String hello(){
        return "Hello Java world !";
    }
}
