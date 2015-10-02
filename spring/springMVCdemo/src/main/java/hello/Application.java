package hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * This file is part of springMVC
 * <p>
 * Created by <a href="http://junjunguo.com">GuoJunjun</a> on October 02, 2015.
 * <p>
 * the @SpringBootApplication is a convenience annotation that adds all of the following:
 * <p>
 * the  @Configuration tags the class as a source of bean definitions for the application context.
 * <p>
 * the @EnableAutoConfiguration tells Spring Boot to start adding beans based on classpath settings,
 * other beans, and various property settings.
 * <p>
 * Normally you would add @EnableWebMvc for a Spring MVC app, but Spring Boot adds it automatically
 * when it sees spring-webmvc on the classpath. This flags the application as a web application and
 * activates key behaviors such as setting up a DispatcherServlet.
 * <p>
 * the @ComponentScan tells Spring to look for other components, configurations, and services in the
 * the hello package, allowing it to find the hello.GreetingController.
 * <p>
 * The main() method uses Spring Boot’s SpringApplication.run() method to launch an application. Did
 * you notice that there wasn’t a single line of XML? No web.xml file either. This web application
 * is 100% pure Java and you didn’t have to deal with configuring any plumbing or infrastructure.
 */
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}