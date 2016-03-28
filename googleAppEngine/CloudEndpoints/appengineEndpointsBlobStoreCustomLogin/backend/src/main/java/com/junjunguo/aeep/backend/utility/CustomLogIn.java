package com.junjunguo.aeep.backend.utility;

/**
 * This file is part of appengineEndpointsBlobStoreOAuthCustomLogin
 * <p>
 * Created by <a href="http://junjunguo.com">GuoJunjun</a> on January 23, 2016.
 */
public class CustomLogIn {
    private String email;
    private String password;

    private CustomLogIn() {}

    /**
     * Instantiates a new Custom log in.
     * @param email    the email
     * @param password the password
     */
    public CustomLogIn(String email, String password) {
        this.email = email;
        this.password = password;
    }

    /**
     * Sets email.
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Sets password.
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets email.
     * @return the email
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Gets password.
     * @return the password
     */
    public String getPassword() {
        return this.password;
    }
}
