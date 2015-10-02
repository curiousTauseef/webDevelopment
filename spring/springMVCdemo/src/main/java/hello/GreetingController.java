package hello;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * This file is part of springMVC
 * <p>
 * Created by <a href="http://junjunguo.com">GuoJunjun</a> on October 02, 2015.
 * <p>
 * In Spring’s approach to building web sites, HTTP requests are handled by a controller. You can
 * easily identify these requests by the @Controller annotation.
 * <p>
 * the hello.GreetingController handles GET requests for /greeting by returning the name of a View, in
 * this case, "greeting". A View is responsible for rendering the HTML content:
 */
@Controller
public class GreetingController {

    /**
     * The @RequestMapping annotation ensures that HTTP requests to /greeting are mapped to the
     * greeting() method.
     * <p>
     * this  example does not specify GET vs. PUT, POST, and so forth, because @RequestMapping maps
     * all HTTP operations by default. Use @RequestMapping(method=GET) to narrow this mapping.
     *
     * @param name
     * @param model
     *
     * @return
     */
    @RequestMapping("/greeting")
    public String greeting(
            /**
             * the @RequestParam binds the value of the query String parameter name into the name
             * parameter of the greeting() method. This query String parameter is not required;
             * if it is absent in the request, the defaultValue of "World" is used. The value of
             * the name parameter is added to a Model object, ultimately making it accessible to
             * the view template.
             */
            @RequestParam(value = "name", required = false, defaultValue = "World") String name,
            Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }

}