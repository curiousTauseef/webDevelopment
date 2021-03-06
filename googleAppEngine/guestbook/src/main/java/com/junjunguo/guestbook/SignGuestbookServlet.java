package com.junjunguo.guestbook;

/**
 * This file is part of guestbook.
 * <p/>
 * Created by <a href="http://junjunguo.com">GuoJunjun</a> on 03/01/16.
 */

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.googlecode.objectify.ObjectifyService;
import com.junjunguo.guestbook.datamodel.Greeting;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Form Handling Servlet Most of the action for this sample is in webapp/guestbook.jsp, which displays the {@link
 * Greeting}'s. This servlet has one method {@link #(<#HttpServletRequest #>, <#HttpServletResponse #>)} which takes the
 * form data and saves it.
 */
public class SignGuestbookServlet extends HttpServlet {

    // Process the http POST of the form
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Greeting greeting;

        UserService userService = UserServiceFactory.getUserService();
        User        user        = userService.getCurrentUser();  // Find out who the user is.

        String guestbookName = req.getParameter("guestbookName");
        String content       = req.getParameter("content");
        if (user != null) {
            greeting = new Greeting(guestbookName, content, user.getUserId(), user.getEmail());
        } else {
            greeting = new Greeting(guestbookName, content);
        }

        // Use Objectify to save the greeting and now() is used to make the call synchronously as we
        // will immediately get a new page using redirect and we want the data to be present.
        ObjectifyService.ofy().save().entity(greeting).now();

        //
//        log("-------- create new book: ");
        //        Book b = new Book();
        //        b.setName("book-" + ((int) Math.random() * 100000));
        //        log("--------create : " + b);
        //        ObjectifyService.ofy().save().entity(b).now();
        //
        resp.sendRedirect("jsp/guestbook.jsp?guestbookName=" + guestbookName);
    }

}