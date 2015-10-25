package com.junjunguo.shr.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by <a href="http://junjunguo.com">GuoJunjun</a> on 25/10/15.
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.junjunguo.shr")
public class AppConfiguration {
}
