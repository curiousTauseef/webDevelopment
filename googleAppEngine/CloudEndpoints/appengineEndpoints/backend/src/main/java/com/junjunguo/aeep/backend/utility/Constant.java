package com.junjunguo.aeep.backend.utility;

/**
 * This file is part of appengineEndpoints
 * <p>
 * Created by <a href="http://junjunguo.com">GuoJunjun</a> on January 07, 2016.
 * <p>
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
    public static final String ANDROID_CLIENT_ID = "YOUR-ANDROID-CLIENT-ID";

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
     * Audience ID used to limit access to some client to the API.
     */
    public static final String AUDIENCE_ID = WEB_CLIENT_ID;

    /**
     * API package name.
     */
    public static final String API_OWNER = "backend.aeep.junjunguo.com";

    /**
     * API package path.
     */
    public static final String API_PACKAGE_PATH = "";


}
