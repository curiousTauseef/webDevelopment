package com.junjunguo.tsag.util;

import org.json.JSONObject;

/**
 * This file is part of tsag
 * <p/>
 * Created by <a href="http://junjunguo.com">GuoJunjun</a> on October 07, 2015.
 */
public class MyJsonParser {
    private String tag;
    private String description;
    private String type;
    private String object;

    public MyJsonParser(String tag, String description) {
        this.tag = tag;
        this.description = description;
        this.type = "";
        this.object = "";
    }

    public MyJsonParser(String tag, String description, String type, String object) {
        this.tag = tag;
        this.description = description;
        this.type = type;
        this.object = object;
    }

    public String toString() {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("tag", this.tag);
            jsonObject.put("desc", this.description);
            jsonObject.put("obj", this.object);
            return jsonObject.toString();

        } catch (Exception e) {
            return null;
        }
    }
}