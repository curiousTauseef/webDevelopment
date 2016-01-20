package com.junjunguo.aeep.activities;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.junjunguo.aeep.R;
import com.junjunguo.aeep.backend.myEndpointsAPI.MyEndpointsAPI;
import com.junjunguo.aeep.backend.myEndpointsAPI.model.Event;
import com.junjunguo.aeep.model.LoginStatus;
import com.junjunguo.aeep.util.ApiBuilderHelper;
import com.junjunguo.aeep.util.AuthenticationHelper;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class EventActivity extends AppCompatActivity {
    private EditText idET;
    private TextView infoTV;
    private Context context;
    private MyEndpointsAPI myEndpointsAPI;
    private AuthenticationHelper authenticationHelper = AuthenticationHelper.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        context = this;
        myEndpointsAPI = ApiBuilderHelper.getInstance().getEndpoints();
        initBtns();
        idET = (EditText) findViewById(R.id.event_et_id);
        infoTV = (TextView) findViewById(R.id.event_tv_info);
    }

    private void initBtns() {
        FloatingActionButton fabAdd = (FloatingActionButton) findViewById(R.id.event_fab_add);
        final Button listBtn, findBtn, deleteBtn, updateBtn;
        listBtn = (Button) findViewById(R.id.event_btn_list_events);
        findBtn = (Button) findViewById(R.id.event_btn_find);
        deleteBtn = (Button) findViewById(R.id.event_btn_delete);
        updateBtn = (Button) findViewById(R.id.event_btn_update);

        //  add a new event
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (authenticationHelper.getLoginStatus() == LoginStatus.LOGGED_OUT) {
                    Toast.makeText(EventActivity.this, "Please Sign In!", Toast.LENGTH_SHORT).show();
                    return;
                }
                startActivity(new Intent(context, EventAddActivity.class));
            }
        });

        //  list all events
        listBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                listEventsService();
            }
        });

        //  find event by id
        findBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String input = idET.getText().toString();
                if (input.length() >= 1) {
                    try {
                        long id = Long.parseLong(input);
                        findEventService(id);
                    } catch (Exception e) {
                        showInfo("error: " + e.getMessage());
                    }
                } else {
                    showInfo("not valid id!");
                }
            }
        });

        //  delete a event
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (authenticationHelper.getLoginStatus() == LoginStatus.LOGGED_OUT) {
                    Toast.makeText(EventActivity.this, "Please Sign In!", Toast.LENGTH_SHORT).show();
                    return;
                }
                String input = idET.getText().toString();
                if (input.length() >= 1) {
                    try {
                        long id = Long.parseLong(input);
                        deleteEventService(id);
                    } catch (Exception e) {
                        showInfo("error: " + e.getMessage());
                    }
                } else {
                    showInfo("not valid id!");
                }
            }
        });

        //  update a event
        updateBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (authenticationHelper.getLoginStatus() == LoginStatus.LOGGED_OUT) {
                    Toast.makeText(EventActivity.this, "Please Sign In!", Toast.LENGTH_SHORT).show();
                    return;
                }
                String input = idET.getText().toString();
                if (input.length() >= 1) {
                    try {
                        long id = Long.parseLong(input);
                        updateEventService(id);
                    } catch (Exception e) {
                        showInfo("error: " + e.getMessage());
                    }
                } else {
                    showInfo("not valid id!");
                }
            }
        });
    }

    private void updateEventService(final long id) {
        new AsyncTask<URL, Void, Event>() {
            @Override
            protected Event doInBackground(URL... params) {
                try {
                    return myEndpointsAPI.eventServices().getEventById(id).execute();
                } catch (IOException e) {
                    showInfo("error: " + e.getMessage());
                    return null;
                }
            }

            @Override
            protected void onPostExecute(Event event) {
                if (event != null) {
                    Intent intent = new Intent(context, EventAddActivity.class);
                    intent.putExtra("UPDATE_EVENT", new Gson().toJson(event));
                    startActivity(intent);
                } else {
                    showInfo("event with id: " + id + " not found!");
                }
            }
        }.execute();
    }

    private void deleteEventService(final long id) {
        new AsyncTask<URL, Void, String>() {
            @Override
            protected String doInBackground(URL... params) {
                try {
                    return "DELETED:  " + myEndpointsAPI.eventServices().deleteEventById(id).execute();
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

    private void findEventService(final long id) {
        new AsyncTask<URL, Void, String>() {
            @Override
            protected String doInBackground(URL... params) {
                try {
                    Event event = myEndpointsAPI.eventServices().getEventById(id).execute();
                    if (event == null) {
                        return "event with: " + id + " not found!";
                    } else {
                        return "found: " + event;
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

    private void listEventsService() {
        new AsyncTask<URL, Void, List>() {
            @Override
            protected List doInBackground(URL... params) {
                try {
                    return myEndpointsAPI.eventServices().listEvents().execute().getItems();
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
                }
            }
        }.execute();
    }


    private void showInfo(String info) {
        infoTV.setText(info);
    }

    public void log(String s) {
        Log.i(this.getClass().getSimpleName(), "- - - - :" + s);
    }
}
