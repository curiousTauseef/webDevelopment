//package com.junjunguo.sae.configuration;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.multipart.support.StandardServletMultipartResolver;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
//
///**
// * Created by <a href="http://junjunguo.com">GuoJunjun</a> on 25/10/15.
// */
//@Configuration
//@EnableWebMvc
//@ComponentScan(basePackages = "com.junjunguo.sae")
//public class AppConfiguration extends WebMvcConfigurerAdapter {
//
//    @Bean(name="multipartResolver")
//    public StandardServletMultipartResolver resolver(){
//        return new StandardServletMultipartResolver();
//    }
//
//    /**
//     * Configure ViewResolvers to deliver preferred views.
//     */
////    @Override
////    public void configureViewResolvers(ViewResolverRegistry registry) {
////
////        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
////        viewResolver.setViewClass(JstlView.class);
////        viewResolver.setPrefix("/jsp/");
////        viewResolver.setSuffix(".jsp");
////        registry.viewResolver(viewResolver);
////    }
//
//    /**
//     * Configure ResourceHandlers to serve static resources like CSS/ Javascript etc...
//     */
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/static/**").addResourceLocations("/static/");
//    }
//
//    /**
//     * Configure MessageSource to lookup any validation/error message in internationalized property files
//     */
////    @Bean
//    //    public MessageSource messageSource() {
//    //        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
//    //        messageSource.setBasename("messages");
//    //        return messageSource;
//    //    }
//}
