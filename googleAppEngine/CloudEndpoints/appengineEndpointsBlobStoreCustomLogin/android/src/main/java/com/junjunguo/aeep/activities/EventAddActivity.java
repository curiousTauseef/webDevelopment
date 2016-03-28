package com.junjunguo.aeep.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.api.client.util.DateTime;
import com.junjunguo.aeep.R;
import com.junjunguo.aeep.backend.myEndpointsAPI.MyEndpointsAPI;
import com.junjunguo.aeep.backend.myEndpointsAPI.model.BlobAccess;
import com.junjunguo.aeep.backend.myEndpointsAPI.model.Event;
import com.junjunguo.aeep.util.ApiBuilderHelper;
import com.junjunguo.aeep.util.AuthenticateHelper;
import com.junjunguo.aeep.util.VideoUploadHelper;

import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class EventAddActivity extends AppCompatActivity {
    private Event event;
    private EditText idEt, titleEt, descriptionEt, pathEt, emailEt, tagsEt;
    private Button confirmBtn, pathBtn;
    private TextView infoTV;
    private MyEndpointsAPI myEndpointsAPI;
    private boolean createNew;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_add);
        context = this;
        myEndpointsAPI = ApiBuilderHelper.getInstance().getEndpoints();
        String updateEvent;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                updateEvent = null;
            } else {
                updateEvent = extras.getString("UPDATE_EVENT");
            }
        } else {
            updateEvent = (String) savedInstanceState.getSerializable("UPDATE_EVENT");
        }

        if (updateEvent != null) {
            try {
                JSONObject jo = new JSONObject(updateEvent);
                long id = jo.getLong("id");
                event = new Event();
                event.setId(id);
                event.setDescription(jo.getString("description"));
                event.setHasVideo(jo.getBoolean("hasVideo"));
                event.setOwnerEmail(jo.getString("ownerEmail"));
                event.setTitle(jo.getString("title"));
                event.setVideoPath(jo.getString("videoPath"));
                JSONObject jot = new JSONObject(jo.getString("uploadTime"));
                DateTime dt = new DateTime(jot.getBoolean("dateOnly"), jot.getLong("value"), jot.getInt("tzShift"));
                event.setUploadTime(dt);
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
            pathBtn = (Button) findViewById(R.id.event_add_btn_path);
            infoTV = (TextView) findViewById(R.id.event_add_tv_info);
            idEt.setKeyListener(null);
            pathEt.setKeyListener(null);
            emailEt.setKeyListener(null);
            if (!createNew) { // update
                idEt.setText(String.valueOf(event.getId()));
                titleEt.setText(event.getTitle());
                emailEt.setText(event.getOwnerEmail());
                descriptionEt.setText(event.getDescription());
                pathEt.setText(event.getVideoPath());
                tagsEt.setText("tags");//TODO
                pathBtn.setText("URL:");
                confirmBtn.setText("update");
            } else {
                pathBtn.setText("PATH:");
                confirmBtn.setText("create");
                if (AuthenticateHelper.getInstance().getAccountName() == null) {
                    Toast.makeText(EventAddActivity.this, "Please Sing In!", Toast.LENGTH_SHORT).show();
                } else {
                    emailEt.setText(AuthenticateHelper.getInstance().getAccountName());
                }
            }
            pathBtn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    if (createNew) {
                        addNewFile(v);
                    } else {
                        Toast.makeText(context, "Video url can not be changed", Toast.LENGTH_SHORT).show();
                    }
                }
            });

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

    private void addNewFile(View v) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("video/*");
        startActivityForResult(intent, SELECT_FILE);
    }


    private static final int SELECT_FILE = 0;
    private Intent data = null;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && resultCode == Activity.RESULT_OK) {
            this.data = data;
            if (data == null) {
                Toast.makeText(context, "There is an error, try it again!", Toast.LENGTH_SHORT).show();
                return;
            }
            pathEt.setText(data.getData().getPath());
        }
    }

    private void confirmAction() {
        if (!AuthenticateHelper.getInstance().isSignedIn()) {
            Toast.makeText(EventAddActivity.this, "Please Sign In!", Toast.LENGTH_SHORT).show();
            return;
        }
        Event e = new Event();
        String email = emailEt.getText().toString();
        String message = "";
        if (!isValidEmail(AuthenticateHelper.getInstance().getAccountName())) {
            log("error! your account: " + AuthenticateHelper.getInstance().getAccountName() + " is not an valid email" +
                    " address");
            message = "error! your account: " + AuthenticateHelper.getInstance().getAccountName() +
                    " is not an valid email  address!";
        } else {
            e.setOwnerEmail(AuthenticateHelper.getInstance().getAccountName());
        }
        if (!isValidEmail(email)) {
            message = "not valid email address !";
        }
        String title = titleEt.getText().toString();
        if (title.length() < 2) {
            message += "\nnot valid title !";
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
            //            e.setVideoPath(path);
            e.setUploadTime(new DateTime(System.currentTimeMillis()));
            // TODO: add tags

            if (data != null && createNew) {
                log("upload file: " + e.toString());
                uploadFile(e);
            } else if (!createNew) {
                performService(e);
            }
        } else {
            showInfo(message);
        }
    }

    private void uploadFile(final Event e) {
        if (!AuthenticateHelper.getInstance().isSignedIn()) {
            Toast.makeText(EventAddActivity.this, "Please Sign In!", Toast.LENGTH_SHORT).show();
            return;
        }
        new AsyncTask<URL, Void, String>() {
            @Override
            protected String doInBackground(URL... params) {
                try {
                    BlobAccess blobAccess = myEndpointsAPI.eventServices().getUploadUrl().execute();
                    String POST_URL = blobAccess.getBlobStoreURL();
                    log("post url: " + POST_URL);
                    VideoUploadHelper multipart = new VideoUploadHelper(POST_URL);
                    multipart.addFilePart("myFile", new File(data.getData().getPath()));
                    return multipart.finish();
                } catch (Exception e) {
                    e.fillInStackTrace();
                    return "error: " + e.getMessage();
                }
            }

            @Override
            protected void onPostExecute(String result) {
                showInfo(result);
                if (!result.contains("error")) {
                    e.setVideoPath(result);
                    performService(e);
                }
            }
        }.execute();
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
