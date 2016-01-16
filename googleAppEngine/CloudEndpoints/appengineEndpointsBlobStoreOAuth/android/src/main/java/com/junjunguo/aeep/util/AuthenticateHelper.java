package com.junjunguo.aeep.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;

/**
 * This file is part of appengineEndpointsBlobStoreOAuth
 * <p/>
 * Created by <a href="http://junjunguo.com">GuoJunjun</a> on January 16, 2016.
 */
public class AuthenticateHelper {
    private static AuthenticateHelper instance;
    private Context context;
    public static final String TAG = "AppEngineEndpoints";
    public static final String PREF_ACCOUNT_NAME = "accountName";
    public static final String PREF_AUTH_TOKEN = "authToken";
    public static final int REQUEST_GOOGLE_PLAY_SERVICES = 0;
    public static final int REQUEST_ACCOUNT_PICKER = 1;

    /**
     * Preference object where the app stores the name of the preferred user.
     */
    private SharedPreferences sharedPreferences;
    private String accountName;

    /**
     * Credentials object that maintains tokens to send to the backend.
     */
    private GoogleAccountCredential credential;

    private boolean signedIn = false;


    public static AuthenticateHelper getInstance() {
        if (instance == null) {
            instance = new AuthenticateHelper();
        }
        return instance;
    }

    private AuthenticateHelper() {
    }


    public void forgetAccount() {
        this.signedIn = false;
        SharedPreferences.Editor editor2 = sharedPreferences.edit();
        editor2.remove(PREF_AUTH_TOKEN);
        editor2.commit();
    }

    public void setAccountName(String accountName) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PREF_ACCOUNT_NAME, accountName);
        editor.commit();
        credential.setSelectedAccountName(accountName);
        this.accountName = accountName;
    }

    public SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }

    public void setSharedPreferences(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    public String getAccountName() {
        return accountName;
    }

    public GoogleAccountCredential getCredential() {
        return credential;
    }

    public void setCredential(GoogleAccountCredential credential) {
        this.credential = credential;
    }

    public boolean isSignedIn() {
        return signedIn;
    }

    public void setSignedIn(boolean signedIn) {
        this.signedIn = signedIn;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
        setCredential(GoogleAccountCredential.usingAudience(context, Constant.AUDIENCE_ID));
        setSharedPreferences(context.getSharedPreferences(TAG, 0));
        setAccountName(sharedPreferences.getString(PREF_ACCOUNT_NAME, null));
    }
}
