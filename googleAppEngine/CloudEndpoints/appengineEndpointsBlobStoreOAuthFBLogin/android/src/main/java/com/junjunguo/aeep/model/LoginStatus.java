package com.junjunguo.aeep.model;

/**
 * This file is part of appengineEndpointsBlobStoreOAuthFBLogin
 * <p/>
 * Created by <a href="http://junjunguo.com">GuoJunjun</a> on January 20, 2016.
 */
public enum LoginStatus {
    LOGGED_OUT(0), GOOGLE(1), FACEBOOK(2), TWITTER(3);

    private final int value;

    private LoginStatus(int value) {
        this.value = value;
    }
}
