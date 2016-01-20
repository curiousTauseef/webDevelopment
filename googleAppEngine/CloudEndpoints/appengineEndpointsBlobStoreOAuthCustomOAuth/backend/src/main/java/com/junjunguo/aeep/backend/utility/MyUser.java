package com.junjunguo.aeep.backend.utility;

import com.google.api.server.spi.auth.common.User;

/**
 * This file is part of appengineEndpointsBlobStoreOAuthCustomOAuth
 * <p>
 * Created by <a href="http://junjunguo.com">GuoJunjun</a> on January 20, 2016.
 */
public class MyUser extends User {
    private String extraData;

    public MyUser(String email) {
        super(email);
        this.extraData = "notasecret";
    }

    public MyUser(String email, String extraData) {
        super(email);
        this.extraData = extraData;
    }
}
