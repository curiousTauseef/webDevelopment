package com.junjunguo.aeep.activities;

import android.accounts.AccountManager;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.junjunguo.aeep.R;
import com.junjunguo.aeep.model.LoginStatus;
import com.junjunguo.aeep.util.AuthenticationHelper;

public class MainActivity extends AppCompatActivity {
    private Context context;
    private AuthenticationHelper authenHelper;
    private LoginButton loginBtnFB;
    private CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(this.getApplicationContext());
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        context = this;
        initBtns();
        initFB();
        authenHelper = AuthenticationHelper.getInstance();
        authenHelper.setContext(getApplicationContext());
        if (authenHelper.getLoginStatus() != LoginStatus.LOGGED_OUT) {
            setSignInEnablement(false);
            setAccountLabel(authenHelper.getAccountName());
        }
    }

    private void initFB() {
        callbackManager = CallbackManager.Factory.create();
        loginBtnFB = (LoginButton) findViewById(R.id.main_btn_fb_login);
        loginBtnFB.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

            }
        });

        loginBtnFB.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                authenHelper.setAccessTokenFB(loginResult.getAccessToken());
                setSignInEnablement(false);
                setAccountLabel(authenHelper.getAccountName());

                new AccessTokenTracker() {
                    @Override
                    protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken,
                            AccessToken currentAccessToken) {
                        if (currentAccessToken == null) {
                            // user logout
                            authenHelper.signOut();
                            setSignInEnablement(true);
                            setAccountLabel("(not signed in)");
                        }
                    }
                };
            }

            @Override
            public void onCancel() {
                // App code
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
                log(exception.getMessage());
            }
        });
    }

    /**
     * Handles logic for clicking the sign in button.
     * @param v current view within the application, for rendering updates
     */
    public void gSignInBtn(View v) {
        if (authenHelper.getLoginStatus() == LoginStatus.LOGGED_OUT) {
            chooseGAccount();
        } else if (authenHelper.getLoginStatus() == LoginStatus.GOOGLE) {// sign out:
            authenHelper.signOut();
            setSignInEnablement(true);
            setAccountLabel("(not signed in)");
        }
    }


    private void chooseGAccount() {
        startActivityForResult(authenHelper.getCredentialG().newChooseAccountIntent(),
                authenHelper.REQUEST_ACCOUNT_PICKER_G);
    }

    private void setSignInEnablement(boolean state) {
        Button button = (Button) findViewById(R.id.main_btn_g_login);
        if (state) {
            button.setText("Sign In");
        } else {
            button.setText("Sign Out");
        }
    }


    private void setAccountLabel(String label) {
        TextView userLabel = (TextView) findViewById(R.id.main_tv_login);
        userLabel.setText(label);
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkGooglePlayServicesAvailable();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == AuthenticationHelper.REQUEST_ACCOUNT_PICKER_G) {
            if (data != null && data.getExtras() != null) {
                String accountName = data.getExtras().getString(AccountManager.KEY_ACCOUNT_NAME);
                if (accountName != null) {
                    authenHelper.setAccountName(accountName);
                    authenHelper.setCredentialG();
                    setSignInEnablement(false);
                    setAccountLabel(authenHelper.getAccountName());
                }
            }
        } else if (requestCode == loginBtnFB.getRequestCode()) {
            callbackManager.onActivityResult(requestCode, resultCode, data);
        }
    }

    /**
     * Check that Google Play services APK is installed and up to date.
     */
    private boolean checkGooglePlayServicesAvailable() {
        final int connectionStatusCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (GooglePlayServicesUtil.isUserRecoverableError(connectionStatusCode)) {
            showGooglePlayServicesAvailabilityErrorDialog(connectionStatusCode);
            return false;
        }
        return true;
    }

    /**
     * Called if the device does not have Google Play Services installed.
     */
    void showGooglePlayServicesAvailabilityErrorDialog(final int connectionStatusCode) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Dialog dialog = GooglePlayServicesUtil.getErrorDialog(connectionStatusCode, MainActivity.this,
                        AuthenticationHelper.REQUEST_GOOGLE_PLAY_SERVICES);
                dialog.show();
            }
        });
    }

    private void initBtns() {
        final Button userBtn, eventBtn, tagBtn, localBtn;
        userBtn = (Button) findViewById(R.id.main_btn_user);
        eventBtn = (Button) findViewById(R.id.main_btn_event);
        tagBtn = (Button) findViewById(R.id.main_btn_tag);
        localBtn = (Button) findViewById(R.id.main_btn_location);

        userBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(context, UserActivity.class));
            }
        });
        eventBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(context, EventActivity.class));
            }
        });
        tagBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(context, TagActivity.class));
            }
        });
        localBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(context, UserActivity.class));
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void log(String s) {
        Log.i(this.getClass().getSimpleName(), "- - - - :" + s);
    }
}
