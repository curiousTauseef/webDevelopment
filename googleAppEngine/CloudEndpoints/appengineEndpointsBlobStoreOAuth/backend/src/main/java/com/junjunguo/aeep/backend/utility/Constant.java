package com.junjunguo.aeep.backend.utility;

/**
 * This file is part of appengineEndpoints
 * <p/>
 * Created by <a href="http://junjunguo.com">GuoJunjun</a> on January 07, 2016.
 * <p/>
 * API Keys, Client Ids and Audience Ids for accessing APIs and configuring Cloud Endpoints.
 */
public final class Constant {

    /**
     * Google Cloud Messaging API key.
     */
    public static final String GCM_API_KEY = "AIzaSyAlGXgCeAYYtLoKOJ8o7AzfHBF5nUb-_uI";

    /**
     * Android client ID from Google Cloud console.
     */
    public static final String ANDROID_CLIENT_ID =
            "994760477407-oac3vklid202l6hi8c7qq5p70j0t53lr.apps.googleusercontent.com";

    /**
     * iOS client ID from Google Cloud console.
     */
    public static final String IOS_CLIENT_ID = "YOUR-IOS-CLIENT-ID";

    /**
     * Web client ID from Google Cloud console.
     */
    public static final String WEB_CLIENT_ID =
            "994760477407-cmndblrro3f9ktskgs7gts2cpe68h25t.apps.googleusercontent.com";

    /**
     * The audiences argument is currently used only for Android clients. The clientIds list protects the backend API
     * from unauthorized clients. But further protection is needed to protect the clients, so that their auth token will
     * work only for the intended backend API. For Android clients, this mechanism is the audiences attribute, in which
     * you specify the client ID of the backend API.
     */
    public static final String AUDIENCE_ID = ANDROID_CLIENT_ID;

    /**
     * API package name.
     */
    public static final String API_OWNER = "backend.aeep.junjunguo.com";

    /**
     * API package path.
     */
    public static final String API_PACKAGE_PATH = "";

}
