package com.junjunguo.aeep.util;

import android.content.Context;

import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;

/**
 * This file is part of appengineEndpointsBlobStoreOAuthCustomOAuth
 * <p/>
 * Created by <a href="http://junjunguo.com">GuoJunjun</a> on January 20, 2016.
 */
public class MyGoogleAccountCredential extends GoogleAccountCredential {
    /**
     * @param context context
     * @param scope   scope to use on {@link GoogleAccountCredential#getToken}
     */
    public MyGoogleAccountCredential(Context context, String scope) {
        super(context, scope);
    }
}
