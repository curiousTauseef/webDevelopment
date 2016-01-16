package com.junjunguo.aeep.util;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.junjunguo.aeep.backend.myEndpointsAPI.MyEndpointsAPI;

/**
 * This file is part of appengineEndpoints
 * <p/>
 * Created by <a href="http://junjunguo.com">GuoJunjun</a> on January 07, 2016.
 */
public class ApiBuilderHelper {


    /**
     * Default constructor, never called.
     */
    private ApiBuilderHelper() {
    }

    /**
     * *
     * @return ShoppingAssistant endpoints to the GAE backend.
     */
    public static MyEndpointsAPI getEndpoints() {

//        MyEndpointsAPI.Builder builder =
//                new MyEndpointsAPI.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(),
//                        getRequestInitializer()).setRootUrl(Constant.LOCAL_URL)
//                        .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
//                            @Override
//                            public void initialize(final AbstractGoogleClientRequest<?> abstractGoogleClientRequest)
//                                    throws IOException {
//                                abstractGoogleClientRequest.setDisableGZipContent(true);
//                            }
//                        });

        MyEndpointsAPI.Builder builder =
                new MyEndpointsAPI.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                        .setRootUrl(Constant.ROOT_URL);

        builder.setApplicationName("guo-junjun");
        return builder.build();
    }

    /**
     * Returns appropriate HttpRequestInitializer depending whether the application is configured to require users to be
     * signed in or not.
     * @return an appropriate HttpRequestInitializer.
     */
    static HttpRequestInitializer getRequestInitializer() {
        if (Constant.SIGN_IN_REQUIRED) {
            return null;
            //            return SignInActivity.getCredential();
        } else {
            return new HttpRequestInitializer() {
                @Override
                public void initialize(final HttpRequest arg0) {
                }
            };
        }
    }
}
