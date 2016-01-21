package com.junjunguo.aeep.util;

import android.util.Log;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.junjunguo.aeep.backend.myEndpointsAPI.MyEndpointsAPI;
import com.junjunguo.aeep.model.LoginStatus;

import java.io.IOException;

/**
 * This file is part of appengineEndpoints
 * <p>
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
                        //                        AuthenticateHelper.getInstance().getCredentialG()).setRootUrl
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
        AuthenticationHelper helper = AuthenticationHelper.getInstance();
        if (helper.getLoginStatus() == LoginStatus.GOOGLE) {
            log("google credential : " + helper.getCredentialG());
            log("google  acc name : " + helper.getAccountName());
            log("google credential name: " + helper.getCredentialG().getSelectedAccountName());
            return helper.getCredentialG();
        } else if (helper.getLoginStatus() == LoginStatus.FACEBOOK) {
            return helper.getCredentialForFB();
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
