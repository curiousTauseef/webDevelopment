//package com.junjunguo.tsag.util;
//
//import org.json.simple.JSONObject;
//
///**
// * Created by GuoJunjun <junjunguo.com> on 13/10/15.
// */
//
//public class Result {
//    private String tag;
//    private String description;
//    private String object;
//
//    public Result(String tag, String description, String object) {
//        this.tag = tag;
//        this.description = description;
//        this.object = object;
//    }
//
//    public Result(String tag, String description) {
//        this.tag = tag;
//        this.description = description;
//    }
//
//    public String getTag() {
//        return tag;
//    }
//
//    public void setTag(String tag) {
//        this.tag = tag;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public String getObject() {
//        return object;
//    }
//
//    public void setObject(String object) {
//        this.object = object;
//    }
//
//    public String toString() {
//        try {
//            JSONObject jsonObject = new JSONObject();
//            jsonObject.put("tag", this.tag);
//            jsonObject.put("desc", this.description);
//            jsonObject.put("obj", this.object);
//            return jsonObject.toString();
//
//        } catch (Exception e) {
//            return null;
//        }
//    }
//}
