#SpringMVC Hibernate MySQL Maven Servlet

##Servlet project structure

```
- src
    - main
        - java
        - resources
        - webapp
            - scripts
            - styles
            - images
            - WEB-INF
                - views
                    - jsp
                    - etc.
    - test
```

####WEB-INF resources are accessible to the resource loader Web-Application and not directly visible for the public.

> **The  WEB-INF node is not part of the public document tree of the application.**
> No file contained in the **WEB-INF** directory may be served directly to a client by the container. 
> However, the contents of the  **WEB-INF** directory are visible to servlet code using the `getResource` and `getResourceAsStream` method calls on the ServletContext, and may be exposed using the `RequestDispatcher` calls.






##Servlet data working flow:
Spring MVC DispatcherServlet will read xml configuration file on the principle:

![](../SpringHibernateWebServer/img/springmvc_dispatcher_servlet.png)

![](../SpringHibernateWebServer/img/image-provider.png)

##path variable

use the @PathVariable annotation on a method argument to bind it to the value of a URI template variable:
For example:
/web/fe/{sitePrefix}/{language}/document/{id}/{naturalText}

![](../SpringHibernateWebServer/img/pathvariable.png)

```java
@Controller
public class PathVariableExampleController {
    /**
     * @PathVariable Example:
     *
     * <pre>
     *
     *   http://o7planning.org/web/fe/default/en/document/8108/spring-mvc-for-beginners
     *   
     *   or
     *   
     *   http://localhost:8080/HelloSpringMVC/web/fe/default/en/document/8108/spring-mvc-for-beginners
     *
     * </pre>
     */
    @RequestMapping("/web/fe/{sitePrefix}/{language}/document/{id}/{naturalText}")
    public String documentView(Model com.junjunguo.spring.model,
            @PathVariable(value = "sitePrefix") String sitePrefix,
            @PathVariable(value = "language") String language,
            @PathVariable(value = "id") Long id,
            @PathVariable(value = "naturalText") String naturalText) {
        com.junjunguo.spring.model.addAttribute("sitePrefix", sitePrefix);
        com.junjunguo.spring.model.addAttribute("language", language);
        com.junjunguo.spring.model.addAttribute("id", id);
        com.junjunguo.spring.model.addAttribute("naturalText", naturalText);
        String documentName = "Java tutorial for Beginners";
        if (id == 8108) {
            documentName = "Spring MVC for Beginners";
        }
        com.junjunguo.spring.model.addAttribute("documentName", documentName);
        return "documentView";
    }
}
```

[image and code source: o7planning](http://o7planning.org/web/fe/default/en/document/8108/spring-mvc-tutorial-for-beginners)


