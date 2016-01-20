package com.junjunguo.aeep.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.facebook.AccessToken;
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;
import com.junjunguo.aeep.model.LoginStatus;

/**
 * This file is part of appengineEndpointsBlobStoreOAuth
 * <p>
 * Created by <a href="http://junjunguo.com">GuoJunjun</a> on January 16, 2016.
 */
public class AuthenticationHelper {
    private static AuthenticationHelper instance;
    private Context context;
    public static final String TAG = "AppEngineEndpoints";
    public static final String PREF_ACCOUNT_NAME = "accountName";
    public static final String PREF_AUTH_TOKEN = "authToken";
    public static final int REQUEST_GOOGLE_PLAY_SERVICES = 0;
    public static final int REQUEST_ACCOUNT_PICKER_G = 1;
    /**
     * Preference object where the app stores the name of the preferred user.
     */
    private SharedPreferences sharedPreferences;
    private String accountName;

    /**
     * Credentials object that maintains tokens to send to the backend.
     */
    private GoogleAccountCredential credentialG;
    private AccessToken accessTokenFB;
    private LoginStatus loginStatus = LoginStatus.LOGGED_OUT;

    public static AuthenticationHelper getInstance() {
        if (instance == null) {
            instance = new AuthenticationHelper();
        }
        return instance;
    }

    private AuthenticationHelper() {
    }

    public void setAccountName(String accountName) {
        sharedPreferences.edit().putString(PREF_ACCOUNT_NAME, accountName).apply();
        this.accountName = accountName;
        if (loginStatus == LoginStatus.GOOGLE) {
            credentialG.setSelectedAccountName(accountName);
        }
    }

    /**
     * Sets google credential, init google login.
     */
    public void setCredentialG() {
        setLoginStatus(LoginStatus.GOOGLE);
        setAccountName(sharedPreferences.getString(PREF_ACCOUNT_NAME, null));
    }

    /**
     * Sets access token for facebook, and init facebook login.
     * @param accessTokenFB the access token fb
     */
    public void setAccessTokenFB(AccessToken accessTokenFB) {
        this.accessTokenFB = accessTokenFB;
        setLoginStatus(LoginStatus.FACEBOOK);
        setAccountName(accessTokenFB.getUserId());
    }

    public void setContext(Context context) {
        this.context = context;
        this.credentialG = GoogleAccountCredential.usingAudience(context, Constant.AUDIENCE_ID);
        setSharedPreferences(context.getSharedPreferences(TAG, 0));
    }

    /**
     * Sign out: clean memory, set saved variables to null.
     */
    public void signOut() {
        setLoginStatus(LoginStatus.LOGGED_OUT);
        sharedPreferences.edit().putString(PREF_ACCOUNT_NAME, null).apply();
        sharedPreferences.edit().putString(PREF_AUTH_TOKEN, null).apply();
        credentialG.setSelectedAccountName(null);
    }

    public GoogleAccountCredential getCredentialForFB() {
        GoogleAccountCredential gac = new GoogleAccountCredential(context, accessTokenFB.getToken());
        gac.setSelectedAccountName(accountName);
        return gac;
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

    public GoogleAccountCredential getCredentialG() {
        return credentialG;
    }

    public AccessToken getAccessTokenFB() {
        return accessTokenFB;
    }

    public LoginStatus getLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(LoginStatus loginStatus) {
        this.loginStatus = loginStatus;
    }

    public Context getContext() {
        return context;
    }
}
