package com.junjunguo.aeep.util;

/**
 * This file is part of appengineEndpoints
 * <p>
 * Created by <a href="http://junjunguo.com">GuoJunjun</a> on January 07, 2016.
 */
public class Constant {
    /**
     * Substitute you own sender ID here. This is the project number you got from the API Console, as described in
     * "Getting Started."
     */
    static final String SENDER_ID = "994760477407";

    /**
     * Web client ID from Google Cloud console.
     */
    static final String WEB_CLIENT_ID = "994760477407-cmndblrro3f9ktskgs7gts2cpe68h25t.apps.googleusercontent.com";

    /**
     * The web client ID from Google Cloud Console.
     */
    static final String AUDIENCE_ANDROID_CLIENT_ID = "server:client_id:" + WEB_CLIENT_ID;

    /**
     * The URL to the API. Default when running locally on your computer: "http://10.0.2.2:8080/_ah/api/"
     * <p>
     * https://android-app-backend.appspot.com/_ah/api/
     */
    public static final String ROOT_URL = "https://guo-junjun.appspot.com/_ah/api/";

    /**
     * Defines whether authentication is required or not.
     */
    public static final boolean SIGN_IN_REQUIRED = false;

}
