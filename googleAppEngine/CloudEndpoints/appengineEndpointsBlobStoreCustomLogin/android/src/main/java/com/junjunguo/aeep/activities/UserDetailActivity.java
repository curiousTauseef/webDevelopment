package com.junjunguo.aeep.activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.google.gson.Gson;
import com.junjunguo.aeep.R;
import com.junjunguo.aeep.backend.myEndpointsAPI.MyEndpointsAPI;
import com.junjunguo.aeep.backend.myEndpointsAPI.model.Person;
import com.junjunguo.aeep.model.Gender;
import com.junjunguo.aeep.util.ApiBuilderHelper;
import com.junjunguo.aeep.util.AuthenticateHelper;

import java.io.IOException;
import java.net.URL;

public class UserDetailActivity extends AppCompatActivity {
    private Person user;
    private EditText emailEt, firstNameEt, lastNameEt, passwordEt;
    private RadioButton maleRb, femaleRb;
    private Button confirmBtn;
    private Gender gender;
    private TextView infoTV;
    private MyEndpointsAPI myEndpointsAPI;
    private boolean createNew;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);
        myEndpointsAPI = ApiBuilderHelper.getInstance().getEndpoints();

        String s;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                s = null;
            } else {
                s = extras.getString("UPDATE_USER");
            }
        } else {
            s = (String) savedInstanceState.getSerializable("UPDATE_USER");
        }

        if (s != null) {
            user = new Gson().fromJson(s, Person.class);
            log("user to update: " + user);
            createNew = false; // update user
        } else {
            createNew = true; // create new user
        }
        init();
    }

    private void init() {
        gender = Gender.UNKNOWN;
        emailEt = (EditText) findViewById(R.id.user_detail_et_email);
        firstNameEt = (EditText) findViewById(R.id.user_detail_et_first_name);
        lastNameEt = (EditText) findViewById(R.id.user_detail_et_last_name);
        passwordEt = (EditText) findViewById(R.id.user_detail_et_password);
        maleRb = (RadioButton) findViewById(R.id.user_detail_radio_male);
        femaleRb = (RadioButton) findViewById(R.id.user_detail_radio_female);
        confirmBtn = (Button) findViewById(R.id.user_detail_btn_confirm);
        infoTV = (TextView) findViewById(R.id.user_detail_tv_info);
        userSignedIn();

        if (!createNew) { // update
            //            emailEt.setKeyListener(null);
            emailEt.setText(user.getEmail());
            firstNameEt.setText(user.getFirstName());
            lastNameEt.setText(user.getLastName());
            passwordEt.setText(user.getPassword());
            confirmBtn.setText("update");

            if (user.getGender().equals(Gender.FEMALE.toString())) {
                femaleRb.setChecked(true);
                maleRb.setChecked(false);
            } else if (user.getGender().equals(Gender.MALE.toString())) {
                femaleRb.setChecked(false);
                maleRb.setChecked(true);
            } else {
                femaleRb.setChecked(false);
                maleRb.setChecked(false);
            }
        } else {
            confirmBtn.setText("register");
            user = new Person();
        }

        confirmBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                confirmAction();
            }
        });
    }

    private void userSignedIn() {
        if (AuthenticateHelper.getInstance().getAccountName() != null) {
            //TODO
            emailEt.setText(AuthenticateHelper.getInstance().getAccountName());
            emailEt.setKeyListener(null);
        }
    }

    private void confirmAction() {
        String email = emailEt.getText().toString();
        String message = "";
        if (!isValidEmail(email)) {
            message = "not valid email address !";
        }
        String firstName = firstNameEt.getText().toString();
        if (firstName.length() < 2) {
            message += "\nnot valid first name !";
        }
        String lastName = lastNameEt.getText().toString();
        if (lastName.length() < 2) {
            message += "\nnot valid last name !";
        }
        String password = passwordEt.getText().toString();
        if (password.length() == 0) {

        } else if (password.length() < 4) {
            message += "\npassword too short !";
        } else {
            user.setPassword(password);
        }
        if (message.length() == 0) {
            user.setEmail(email);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setGender(gender.toString());
            log("before api call");

            performService(user);
        } else {
            showInfo(message);
        }
    }

    private void performService(final Person user) {
        new AsyncTask<URL, Void, String>() {
            @Override
            protected String doInBackground(URL... params) {
                try {
                    if (createNew) {
                        return "create user: " + myEndpointsAPI.userServices().createUser(user).execute();
                    } else {
                        return "update user: " + myEndpointsAPI.userServices().updateUser(user).execute();
                    }
                } catch (IOException e) {
                    return "error: " + e.getMessage();
                }
            }

            @Override
            protected void onPostExecute(String result) {
                showInfo(result);
            }
        }.execute();
    }


    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.user_detail_radio_male:
                if (checked) gender = Gender.MALE;
                break;
            case R.id.user_detail_radio_female:
                if (checked) gender = Gender.FEMALE;
                break;
        }
    }

    /**
     * @param info showing on text view
     */

    private void showInfo(String info) {
        infoTV.setText(info);
        log(info);
    }

    public boolean isValidEmail(CharSequence target) {
        if (target == null) return false;
        return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    public void log(String s) {
        Log.i(this.getClass().getSimpleName(), "- - - - :" + s);
    }
}
