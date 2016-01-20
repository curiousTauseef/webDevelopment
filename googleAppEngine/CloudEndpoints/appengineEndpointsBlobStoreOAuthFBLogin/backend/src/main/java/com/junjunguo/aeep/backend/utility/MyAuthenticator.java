package com.junjunguo.aeep.backend.utility;


import com.google.api.server.spi.config.Authenticator;

import javax.servlet.http.HttpServletRequest;

/**
 * This file is part of appengineEndpointsBlobStoreOAuthCustomOAuth
 * <p>
 * Created by <a href="http://junjunguo.com">GuoJunjun</a> on January 20, 2016.
 */
public class MyAuthenticator implements Authenticator {
    public MyUser authenticate(HttpServletRequest req) {
        String token = req.getHeader("Authorization");
        if (token != null) {
            //            String user = authenticateFacebook(token);  // apply your Facebook auth.

            //            if (user != null) {
            //                return new MyUser(user);
            //            }
        }
        return null;
    }
}
