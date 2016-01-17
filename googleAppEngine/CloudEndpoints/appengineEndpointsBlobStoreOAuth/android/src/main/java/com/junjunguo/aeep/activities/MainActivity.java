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

import com.google.android.gms.common.GooglePlayServicesUtil;
import com.junjunguo.aeep.R;
import com.junjunguo.aeep.util.AuthenticateHelper;

public class MainActivity extends AppCompatActivity {
    private Context context;
    private AuthenticateHelper authenHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        context = this;
        initBtns();
        authenHelper = AuthenticateHelper.getInstance();
        authenHelper.setContext(getApplicationContext());
        if (authenHelper.getCredential().getSelectedAccountName() != null) {
            onSignIn();
        }
    }

    /**
     * Handles logic for clicking the sign in button.
     * @param v current view within the application, for rendering updates
     */
    public void signIn(View v) {
        if (!authenHelper.isSignedIn()) {
            chooseAccount();
        } else {// sign out:
            authenHelper.signOut();
            setSignInEnablement(true);
            setAccountLabel("(not signed in)");
        }
    }

    private void chooseAccount() {
        startActivityForResult(authenHelper.getCredential().newChooseAccountIntent(),
                authenHelper.REQUEST_ACCOUNT_PICKER);
    }

    private void setSignInEnablement(boolean state) {
        Button button = (Button) findViewById(R.id.main_btn_login);
        if (state) {
            button.setText("Sign In");
        } else {
            button.setText("Sign Out");
        }
    }

    private void onSignIn() {
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
                        onSignIn();
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
