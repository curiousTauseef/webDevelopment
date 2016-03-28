package com.junjunguo.aeep.util;

import android.util.Log;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.junjunguo.aeep.backend.myEndpointsAPI.MyEndpointsAPI;

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
                new MyEndpointsAPI.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                        .setRootUrl(Constant.ROOT_URL);

        builder.setApplicationName("guo-junjun");
        return builder.build();
    }

    public void log(String s) {
        Log.i(this.getClass().getSimpleName(), "- - - - :" + s);
    }
}
