SPRING INITIALIZR

simple web app build by [SPRING INITIALIZR](https://start.spring.io/)

run on browser:
http://localhost:8080/

```java
@SpringBootApplication
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
```

controller:

```java
@RestController
public class ExampleController {
    @RequestMapping("/")
    public String hello(){
        return "Hello Java world !";
    }
}
```