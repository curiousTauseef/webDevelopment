package com.junjunguo.aeep;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.google.gson.Gson;
import com.junjunguo.backend.myEndpointsAPI.MyEndpointsAPI;
import com.junjunguo.backend.myEndpointsAPI.model.User;

import java.io.IOException;

public class UserDetailActivity extends AppCompatActivity {
    private User user;
    private EditText emailEt, firstNameEt, lastNameEt;
    private RadioButton maleRb, femaleRb;
    private Button confirmBtn;
    private Gender gender;
    private TextView infoTV;
    private MyEndpointsAPI myEndpointsAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);
        myEndpointsAPI = ApiBuilderHelper.getEndpoints();
        if (savedInstanceState != null) {
            user = new Gson().fromJson(savedInstanceState.getString("UPDATE_USER"), User.class);
            init(user);
        } else {
            //            user = new User();
            init(null);
        }
    }

    private void init(User user) {
        gender = Gender.UNKNOWN;
        emailEt = (EditText) findViewById(R.id.user_detail_et_email);
        firstNameEt = (EditText) findViewById(R.id.user_detail_et_first_name);
        lastNameEt = (EditText) findViewById(R.id.user_detail_et_last_name);
        maleRb = (RadioButton) findViewById(R.id.user_detail_radio_male);
        femaleRb = (RadioButton) findViewById(R.id.user_detail_radio_female);
        confirmBtn = (Button) findViewById(R.id.user_detail_btn_confirm);
        infoTV = (TextView) findViewById(R.id.user_detail_tv_info);
        if (user != null) {
            emailEt.setKeyListener(null);
            emailEt.setText(user.getEmail());
            firstNameEt.setText(user.getFirstName());
            lastNameEt.setText(user.getLastName());
            confirmBtn.setText("update");
            if (user.getGender().equals(Gender.FEMALE)) {
                femaleRb.setChecked(true);
                maleRb.setChecked(false);
            } else if (user.getGender().equals(Gender.MALE)) {
                femaleRb.setChecked(false);
                maleRb.setChecked(true);
            } else {
                femaleRb.setChecked(false);
                maleRb.setChecked(false);
            }
        } else {
            confirmBtn.setText("register");
            this.user = new User();
        }

        confirmBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                confirmAction();
            }
        });
    }

    private void confirmAction() {
        try {
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
            if (message.length() == 0) {
                user.setEmail(email);
                user.setFirstName(firstName);
                user.setLastName(lastName);
                user.setGender(gender.toString());
                log("before api call");
                try {
                    log("print user: " + user.toString());
                    //                    myEndpointsAPI.userServices().updateUser(user);
                    showInfo(myEndpointsAPI.userServices().createUser(user).toString());
                } catch (IOException e) {
                    log("! ! ! ! ! error:");
                    e.printStackTrace();
                }
            } else {
                showInfo(message);
            }
        } catch (Exception e) {
            log(e.toString());
        }
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
    }

    public boolean isValidEmail(CharSequence target) {
        if (target == null) return false;
        return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    public void log(String s) {
        Log.i(this.getClass().getSimpleName(), "- - - - :" + s);
    }
}
