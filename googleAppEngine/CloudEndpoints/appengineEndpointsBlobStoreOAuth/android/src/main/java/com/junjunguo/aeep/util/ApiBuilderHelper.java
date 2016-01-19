package com.junjunguo.aeep.util;

import android.util.Log;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.junjunguo.aeep.backend.myEndpointsAPI.MyEndpointsAPI;

import java.io.IOException;

/**
 * This file is part of appengineEndpoints
 * <p/>
 * Created by <a href="http://junjunguo.com">GuoJunjun</a> on January 07, 2016.
 */
public class ApiBuilderHelper {

    private static ApiBuilderHelper instance;

    public static ApiBuilderHelper getInstance() {
        if (instance == null) {
            instance = new ApiBuilderHelper();
        }
        return instance;
    }

    /**
     * Default constructor, never called.
     */
    private ApiBuilderHelper() {
    }

    /**
     * *
     * @return ShoppingAssistant endpoints to the GAE backend.
     */
    public MyEndpointsAPI getEndpoints() {
        MyEndpointsAPI.Builder builder =
                new MyEndpointsAPI.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(),
                        //                        AuthenticateHelper.getInstance().getCredential()).setRootUrl
                        // (Constant.ROOT_URL)
                        getRequestInitializer()).setRootUrl(Constant.ROOT_URL)
                        .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                            @Override
                            public void initialize(final AbstractGoogleClientRequest<?> abstractGoogleClientRequest)
                                    throws IOException {
                                abstractGoogleClientRequest.setDisableGZipContent(true);
                            }
                        });

        builder.setApplicationName("guo-junjun");
        return builder.build();
    }

    /**
     * Returns appropriate HttpRequestInitializer depending whether the application is configured to require users to be
     * signed in or not.
     * @return an appropriate HttpRequestInitializer.
     */
    private HttpRequestInitializer getRequestInitializer() {
        if (AuthenticateHelper.getInstance().isSignedIn()) {
            log(AuthenticateHelper.getInstance().getCredential().toString());
            return AuthenticateHelper.getInstance().getCredential();
        } else {
            return new HttpRequestInitializer() {
                @Override
                public void initialize(final HttpRequest arg0) {
                }
            };
        }
    }

    public void log(String s) {
        Log.i(this.getClass().getSimpleName(), "- - - - :" + s);
    }
}
