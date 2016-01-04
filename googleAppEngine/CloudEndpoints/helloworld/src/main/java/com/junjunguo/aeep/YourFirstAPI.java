package com.junjunguo.aeep;

/**
 * Add your first API methods in this class, or you may create another class. In that case, please update your web.xml
 * accordingly.
 **/

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

import javax.inject.Named;

/**
 * An endpoint class we are exposing
 */
@Api(name = "myApi",
     version = "v1",
     namespace = @ApiNamespace(ownerDomain = "aeep.junjunguo.com",
                               ownerName = "aeep.junjunguo.com",
                               packagePath = ""))
public class YourFirstAPI {

    /**
     * A simple endpoint method that takes a name and says Hi back
     */
    @ApiMethod(name = "sayHi")
    public MyBean sayHi(
            @Named("name")
            String name) {
        MyBean response = new MyBean();
        response.setData("Hi, " + name + " form " + this.getClass().getSimpleName() + " data in the bean.");

        return response;
    }

}
