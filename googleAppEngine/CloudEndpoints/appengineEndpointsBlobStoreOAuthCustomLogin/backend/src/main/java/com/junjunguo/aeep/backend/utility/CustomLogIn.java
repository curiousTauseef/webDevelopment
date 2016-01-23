package com.junjunguo.aeep.backend.utility;

/**
 * This file is part of appengineEndpointsBlobStoreOAuthCustomLogin
 * <p/>
 * Created by <a href="http://junjunguo.com">GuoJunjun</a> on January 23, 2016.
 */
public class CustomLogIn {
    private String email;
    private String password;

    private CustomLogIn() {}

    public CustomLogIn(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }
}
