package com.junjunguo.aeep.activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.api.client.util.DateTime;
import com.junjunguo.aeep.R;
import com.junjunguo.aeep.backend.myEndpointsAPI.MyEndpointsAPI;
import com.junjunguo.aeep.backend.myEndpointsAPI.model.Event;
import com.junjunguo.aeep.util.ApiBuilderHelper;

import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;

public class EventAddActivity extends AppCompatActivity {
    private Event event;
    private EditText idEt, titleEt, descriptionEt, pathEt, emailEt, tagsEt;
    private Button confirmBtn;
    private TextView infoTV;
    private MyEndpointsAPI myEndpointsAPI;
    private boolean createNew;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_add);
        myEndpointsAPI = ApiBuilderHelper.getEndpoints();
        String s;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                s = null;
            } else {
                s = extras.getString("UPDATE_EVENT");
            }
        } else {
            s = (String) savedInstanceState.getSerializable("UPDATE_EVENT");
        }

        if (s != null) {
            try {
                //                log("json: " + s);
                JSONObject jo = new JSONObject(s);
                long id = jo.getLong("id");
                //                log("id: " + id);
                event = new Event();
                event.setId(id);
                event.setDescription(jo.getString("description"));
                event.setHasVideo(jo.getBoolean("hasVideo"));
                event.setOwnerEmail(jo.getString("ownerEmail"));
                event.setTitle(jo.getString("title"));
                event.setVideoPath(jo.getString("videoPath"));
                JSONObject jot = new JSONObject(jo.getString("uploadTime"));
                DateTime dt = new DateTime(jot.getBoolean("dateOnly"), jot.getLong("value"), jot.getInt("tzShift"));
                //                event = new Gson().fromJson(s, Event.class);
                //                log("event to update: " + event);
                createNew = false; // update event
            } catch (Exception e) {
                e.printStackTrace();
                log(e.getMessage());
            }
        } else {
            createNew = true; // create new event
        }
        init();
    }

    private void init() {
        try {
            idEt = (EditText) findViewById(R.id.event_add_et_id);
            titleEt = (EditText) findViewById(R.id.event_add_et_title);
            emailEt = (EditText) findViewById(R.id.event_add_et_email);
            descriptionEt = (EditText) findViewById(R.id.event_add_et_description);
            pathEt = (EditText) findViewById(R.id.event_add_et_path);
            tagsEt = (EditText) findViewById(R.id.event_add_et_tags);
            confirmBtn = (Button) findViewById(R.id.event_add_btn_confirm);
            infoTV = (TextView) findViewById(R.id.event_add_tv_info);
            idEt.setKeyListener(null);
            if (!createNew) { // update
                idEt.setText(String.valueOf(event.getId()));
                titleEt.setText(event.getTitle());
                emailEt.setText(event.getOwnerEmail());
                descriptionEt.setText(event.getDescription());
                pathEt.setText(event.getVideoPath());
                tagsEt.setText("tags");//TODO
                confirmBtn.setText("update");
            } else {
                confirmBtn.setText("create");
            }

            confirmBtn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    confirmAction();
                }
            });
        } catch (Exception e) {
            showInfo("error: " + e.getMessage());
            log(e.getMessage());
        }
    }

    private void confirmAction() {
        Event e = new Event();
        String email = emailEt.getText().toString();
        String message = "";
        if (!isValidEmail(email)) {
            message = "not valid email address !";
        }
        String title = titleEt.getText().toString();
        if (title.length() < 2) {
            message += "\nnot valid title !";
        }
        String path = pathEt.getText().toString();
        if (path.length() < 2) {
            message += "\nnot valid path !";
        }
        if (message.length() == 0) {
            if (event != null) {
                e.setId(event.getId());
            } else {
                e.setId(null);
            }
            e.setTitle(title);
            e.setOwnerEmail(email);
            e.setDescription(descriptionEt.getText().toString());
            e.setVideoPath(path);
            e.setUploadTime(new DateTime(System.currentTimeMillis()));
            // TODO: add tags
            performService(e);
        } else {
            showInfo(message);
        }
    }

    private void performService(final Event e) {
        new AsyncTask<URL, Void, String>() {
            @Override
            protected String doInBackground(URL... params) {
                try {
                    if (createNew) {
                        return "create event: " + myEndpointsAPI.eventServices().createEvent(e).execute();
                    } else {
                        return "update event: " + myEndpointsAPI.eventServices().updateEvent(e).execute();
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
