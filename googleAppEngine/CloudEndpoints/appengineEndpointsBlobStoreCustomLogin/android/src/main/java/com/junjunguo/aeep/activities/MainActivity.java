package com.junjunguo.aeep.activities;

import android.accounts.AccountManager;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.common.GooglePlayServicesUtil;
import com.junjunguo.aeep.R;
import com.junjunguo.aeep.backend.myEndpointsAPI.MyEndpointsAPI;
import com.junjunguo.aeep.backend.myEndpointsAPI.model.ApiResult;
import com.junjunguo.aeep.backend.myEndpointsAPI.model.CustomLogIn;
import com.junjunguo.aeep.util.ApiBuilderHelper;
import com.junjunguo.aeep.util.AuthenticateHelper;

import java.io.IOException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private Context context;
    private AuthenticateHelper authenHelper;
    private MyEndpointsAPI myEndpointsAPI;
    private Button btnGoogle, btnSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        context = this;
        initBtns();
        myEndpointsAPI = ApiBuilderHelper.getInstance().getEndpoints();
        authenHelper = AuthenticateHelper.getInstance();
        authenHelper.setContext(getApplicationContext());
        if (authenHelper.getCredential().getSelectedAccountName() != null) {
            userSignInSucceed();
        }
    }

    /**
     * Handles logic for clicking the sign in button.
     * @param v current view within the application, for rendering updates
     */
    public void googleSignIn(View v) {
        if (authenHelper.isCustomSignIn()) {
            log("custom signed in");
            return;
        }
        if (!authenHelper.isSignedIn()) {
            chooseGoogleAccount();
            authenHelper.setCustomSignIn(false);
        } else {// sign out:
            authenHelper.signOut();
            setSignInEnablement(true);
            setAccountLabel("(not signed in)");
        }
    }

    public void customSignIn(View v) {
        if (!authenHelper.isCustomSignIn() && authenHelper.isSignedIn()) {
            return;
        }
        if (!authenHelper.isSignedIn()) {
            customSignInReq();
        } else {// sign out:
            authenHelper.signOut();
            setSignInEnablement(true);
            setAccountLabel("(not signed in)");
        }
    }

    private void customSignInReq() {
        EditText email = (EditText) findViewById(R.id.main_et_email);
        EditText password = (EditText) findViewById(R.id.main_et_password);
        authenHelper.setAccountName(email.getText().toString() + password.getText().toString());
        CustomLogIn sli = new CustomLogIn();
        sli.setEmail(email.getText().toString());
        sli.setPassword(password.getText().toString());
        customLoginService(sli);
    }

    private void customLoginService(final CustomLogIn customLogIn) {
        new AsyncTask<URL, Void, ApiResult>() {
            @Override
            protected ApiResult doInBackground(URL... params) {
                try {
                    return myEndpointsAPI.userServices().customLogin(customLogIn).execute();
                } catch (IOException e) {
                    ApiResult result = new ApiResult();
                    result.setStatusCode("error");
                    result.setMessage(e.getMessage());
                    return result;
                }
            }

            @Override
            protected void onPostExecute(ApiResult result) {
                if (result.getStatusCode().equalsIgnoreCase("OK")) {// signed in
                    authenHelper.setCustomSignIn(true);
                    authenHelper.setSignedIn(true);
                    userSignInSucceed();
                    log(result.getMessage());
                }
                log(result.getMessage());
            }
        }.execute();
    }


    private void chooseGoogleAccount() {
        startActivityForResult(authenHelper.getCredential().newChooseAccountIntent(),
                authenHelper.REQUEST_ACCOUNT_PICKER);
    }

    private void setSignInEnablement(boolean state) {
        if (authenHelper.isCustomSignIn()) {
            if (state) {
                btnSignIn.setText("Sign In");
            } else {
                btnSignIn.setText("Sign Out");
            }
        } else {
            if (state) {
                btnGoogle.setText("Sign In");
            } else {
                btnGoogle.setText("Sign Out");
            }
        }
    }

    private void userSignInSucceed() {
        authenHelper.setSignedIn(true);
        setSignInEnablement(false);
        setAccountLabel(authenHelper.getAccountName());
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
        switch (requestCode) {
            case AuthenticateHelper.REQUEST_ACCOUNT_PICKER:
                if (data != null && data.getExtras() != null) {
                    String accountName = data.getExtras().getString(AccountManager.KEY_ACCOUNT_NAME);
                    if (accountName != null) {
                        authenHelper.setAccountName(accountName);
                        userSignInSucceed();
                    }
                }
                break;
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
                        AuthenticateHelper.REQUEST_GOOGLE_PLAY_SERVICES);
                dialog.show();
            }
        });
    }

    private void initBtns() {
        btnGoogle = (Button) findViewById(R.id.main_btn_google_login);
        btnSignIn = (Button) findViewById(R.id.main_btn_custom_login);

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
