package com.junjunguo.aeep.activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.junjunguo.aeep.R;
import com.junjunguo.aeep.backend.myEndpointsAPI.MyEndpointsAPI;
import com.junjunguo.aeep.util.ApiBuilderHelper;
import com.junjunguo.aeep.util.AuthenticateHelper;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class TagActivity extends AppCompatActivity {
    private Button btnShow, btnCreate, btnDelId, btnDelLabel;
    private EditText etTag;
    private TextView tvTags;
    private MyEndpointsAPI myEndpointsAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tag);
        myEndpointsAPI = ApiBuilderHelper.getInstance().getEndpoints();
        initView();
    }

    private void initView() {
        btnCreate = (Button) findViewById(R.id.tag_btn_create);
        btnShow = (Button) findViewById(R.id.tag_btn_show);
        //        btnDelId = (Button) findViewById(R.id.tag_btn_del_id);
        btnDelLabel = (Button) findViewById(R.id.tag_btn_del_tag);
        etTag = (EditText) findViewById(R.id.tag_et_new);
        tvTags = (TextView) findViewById(R.id.tag_tv_show_tags);
        initActions();
    }

    private void initActions() {
        btnCreate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String label = etTag.getText().toString();
                if (label.length() >= 1) {
                    createTagService(label);
                }
            }
        });
        btnShow.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showTagsService();
            }
        });
        //        btnDelId.setOnClickListener(new View.OnClickListener() {
        //            public void onClick(View v) {
        //                String input = etTag.getText().toString();
        //                if (input.length() == 0) {
        //                    show("please write tag id !");
        //                    return;
        //                }
        //                Long id;
        //                try {
        //                    id = Long.parseLong(input);
        //                    deleteService(id, null);
        //                } catch (NumberFormatException e) {
        //                    show("error: " + e.getMessage());
        //                }
        //            }
        //        });
        btnDelLabel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String input = etTag.getText().toString();
                if (input.length() == 0) {
                    show("please write tag label !");
                    return;
                }
                try {
                    deleteService(input);
                } catch (NumberFormatException e) {
                    show("error: " + e.getMessage());
                }
            }
        });
    }

    //    private void deleteService(final Long id, final String label) {
    private void deleteService(final String label) {
        new AsyncTask<URL, Void, String>() {
            @Override
            protected String doInBackground(URL... params) {
                try {
                    //                    if (id != null) {
                    //                        return "DELETED:  " + myEndpointsAPI.tagServices().deleteTagById(id)
                    // .execute();
                    //                    }
                    return "DELETED:  " + myEndpointsAPI.tagServices().deleteTagByLabel(label).execute();

                } catch (IOException e) {
                    return "error: " + e.getMessage();
                }
            }

            @Override
            protected void onPostExecute(String result) {
                show(result);
            }
        }.execute();
    }

    private void showTagsService() {
        new AsyncTask<URL, Void, List>() {
            @Override
            protected List doInBackground(URL... params) {
                try {
                    return myEndpointsAPI.tagServices().listTags().execute().getItems();
                } catch (IOException e) {
                    show(e.getMessage());
                    return null;
                }
            }

            @Override
            protected void onPostExecute(List list) {
                if (list != null) {
                    show(list.toString());
                }
            }
        }.execute();
    }

    private void createTagService(final String label) {
        new AsyncTask<URL, Void, String>() {
            @Override
            protected String doInBackground(URL... params) {
                try {
                    log("create tag:" + label);
                    return "create tag: " + myEndpointsAPI.tagServices().createTag(label)
                            .setOauthToken(AuthenticateHelper.getInstance().getCredential().getToken()).execute();
                } catch (Exception e) {
                    e.fillInStackTrace();
                    return "error: " + e.getMessage();
                }
            }

            @Override
            protected void onPostExecute(String result) {
                show(result);
            }
        }.execute();
    }

    private void show(String info) {
        try {
            tvTags.setText(info);
        } catch (Exception e) {
            e.fillInStackTrace();
        }
    }

    public void log(String s) {
        Log.i(this.getClass().getSimpleName(), "- - - - :" + s);
    }

}
