package com.junjunguo.aeep;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.junjunguo.backend.myEndpointsAPI.MyEndpointsAPI;

import java.io.IOException;

public class UserActivity extends AppCompatActivity {
    private EditText emailET;
    private TextView inforTV;
    private Context context;
    private MyEndpointsAPI myEndpointsAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        context = this;
        myEndpointsAPI = ApiBuilderHelper.getEndpoints();
        initBtns();
        emailET = (EditText) findViewById(R.id.user_et_email);
        inforTV = (TextView) findViewById(R.id.user_text_view);
    }

    private void initBtns() {
        FloatingActionButton fabAdd = (FloatingActionButton) findViewById(R.id.user_fab_add);
        final Button listBtn, findBtn, deleteBtn, updateBtn;
        listBtn = (Button) findViewById(R.id.user_btn_list_users);
        findBtn = (Button) findViewById(R.id.user_btn_find);
        deleteBtn = (Button) findViewById(R.id.user_btn_delete);
        updateBtn = (Button) findViewById(R.id.user_btn_update);

        //  add a new user
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context, UserDetailActivity.class));
            }
        });

        //  list all users
        listBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    showInfo(myEndpointsAPI.userServices().listUsers().toString());
                } catch (IOException e) {
                    showInfo(e.getMessage());
                }
            }
        });

        //  find user by email
        findBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String email = emailET.getText().toString();
                if (isValidEmail(email)) {
                    try {
                        showInfo(myEndpointsAPI.userServices().getUserByEmail(email).toString());
                    } catch (IOException e) {
                        showInfo(e.getMessage());
                    }
                } else {
                    showInfo("not valid email address!");
                }
            }
        });

        //  delete a user
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String email = emailET.getText().toString();
                if (isValidEmail(email)) {
                    try {
                        showInfo("DELETED:  " + myEndpointsAPI.userServices().deleteUserByEmail(email).toString());
                    } catch (IOException e) {
                        showInfo(e.getMessage());
                    }
                } else {
                    showInfo("not valid email address!");
                }
            }
        });

        //  update a user
        updateBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String email = emailET.getText().toString();
                if (isValidEmail(email)) {
                    try {
                        showInfo(myEndpointsAPI.userServices().getUserByEmail(email).toString());
                        startActivity(new Intent(context, UserDetailActivity.class));
                    } catch (IOException e) {
                        showInfo(e.getMessage());
                    }
                } else {
                    showInfo("not valid email address!");
                }
            }
        });
    }

    /**
     * @param info showing on text view
     */

    private void showInfo(String info) {
        inforTV.setText(info);
    }

    public boolean isValidEmail(CharSequence target) {
        if (target == null) return false;
        return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }
}
