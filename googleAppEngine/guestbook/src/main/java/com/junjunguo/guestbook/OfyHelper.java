package com.junjunguo.guestbook;

/**
 * This file is part of guestbook.
 * <p/>
 * Created by <a href="http://junjunguo.com">GuoJunjun</a> on 03/01/16.
 */

import com.googlecode.objectify.ObjectifyService;
import com.junjunguo.guestbook.datamodel.Greeting;
import com.junjunguo.guestbook.datamodel.Guestbook;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * OfyHelper, a ServletContextListener, is setup in web.xml to run before a JSP is run.  This is required to let JSP's
 * access Ofy.
 **/
public class OfyHelper implements ServletContextListener {
    public void contextInitialized(ServletContextEvent event) {
        // This will be invoked as part of a warmup request, or the first user request if no warmup
        // request.
        ObjectifyService.register(Guestbook.class);
        ObjectifyService.register(Greeting.class);
    }

    public void contextDestroyed(ServletContextEvent event) {
        // App Engine does not currently invoke this method.
    }
}
