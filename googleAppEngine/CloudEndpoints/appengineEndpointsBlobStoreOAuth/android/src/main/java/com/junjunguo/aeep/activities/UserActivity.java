package com.junjunguo.aeep.activities;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.junjunguo.aeep.R;
import com.junjunguo.aeep.backend.myEndpointsAPI.MyEndpointsAPI;
import com.junjunguo.aeep.backend.myEndpointsAPI.model.Person;
import com.junjunguo.aeep.util.ApiBuilderHelper;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class UserActivity extends AppCompatActivity {
    private EditText emailET;
    private TextView infoTV;
    private Context context;
    private MyEndpointsAPI myEndpointsAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        context = this;
        myEndpointsAPI = ApiBuilderHelper.getInstance().getEndpoints();
        initBtns();
        emailET = (EditText) findViewById(R.id.user_et_email);
        infoTV = (TextView) findViewById(R.id.user_tv_info);
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
                listUsersService();
            }
        });

        //  find user by email
        findBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String email = emailET.getText().toString();
                if (isValidEmail(email)) {
                    findUserService(email);
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
                    deleteService(email);
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
                    updateService(email);
                } else {
                    showInfo("not valid email address!");
                }
            }
        });
    }

    private void updateService(final String email) {
        new AsyncTask<URL, Void, Person>() {
            @Override
            protected Person doInBackground(URL... params) {
                try {
                    return myEndpointsAPI.userServices().getUserByEmail(email).execute();
                } catch (IOException e) {
                    showInfo("error: " + e.getMessage());
                    return null;
                }
            }

            @Override
            protected void onPostExecute(Person user) {
                if (user != null) {
                    Intent intent = new Intent(context, UserDetailActivity.class);
                    intent.putExtra("UPDATE_USER", new Gson().toJson(user));
                    startActivity(intent);
                } else {
                    showInfo("user with email: " + email + " not found!");
                }
            }
        }.execute();
    }

    private void deleteService(final String email) {
        new AsyncTask<URL, Void, String>() {
            @Override
            protected String doInBackground(URL... params) {
                try {
                    return "DELETED:  " + myEndpointsAPI.userServices().deleteUserByEmail(email).execute();
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

    private void findUserService(final String email) {
        new AsyncTask<URL, Void, String>() {
            @Override
            protected String doInBackground(URL... params) {
                try {
                    Person user = myEndpointsAPI.userServices().getUserByEmail(email).execute();
                    if (user == null) {
                        return "user with: " + email + " not found!";
                    } else {
                        return "found: " + user;
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

    private void listUsersService() {
        new AsyncTask<URL, Void, List>() {
            @Override
            protected List doInBackground(URL... params) {
                try {
                    return myEndpointsAPI.userServices().listUsers().execute().getItems();
                } catch (IOException e) {
                    showInfo(e.getMessage());
                    log(e.getMessage());
                    return null;
                }
            }

            @Override
            protected void onPostExecute(List list) {
                log("on post execute: " + list);
                if (list != null) {
                    showInfo(list.toString());
                } else {
                    showInfo("result: " + list);
                }
            }
        }.execute();
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
